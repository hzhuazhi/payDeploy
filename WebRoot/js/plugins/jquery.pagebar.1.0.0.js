/// <reference path="jquery.js"/>

/*
 * jpagebar
 * version: 1.0.0 (01/12/2009)
 * @ jQuery v1.3.*
 *
 * 
 * usage as:
 *     idea.fn.jpagebar({ 
 *               //pagebar 对象div
 *               renderTo: idea("#pagebarDiv2"),               
 *               //总页码
 *               totalpage: 10,
 *               //当前页码
 *               currentPage: 1,
 *               //分页条样式
 *               pagebarCssName: 'pagebar',  
 *               //页码样式
 *               pageNumberCssName:'page_number',
 *               //首页、Pre、Next、尾页样式
 *               pageNameCssName:'pageName',
 *               //选中首页、Pre或Next、尾页样式
 *               currentPageNameCssName:'current_pageName',
 *               //当前选中页码样式
 *               currentPageNumberCssName:'current_page_number',
 *               //显示总页码样式
 *               totalpageNumberCssName:'totalpage_number',  
 *               //点击页码action
 *               onClickPage : function(pageIndex){
 *                               //重新定义jpagebar的显示
 *                               idea.fn.setCurrentPage(this,pageIndex);
 *                               //用户其他操作在这里
 *                               //...
 *                           }
 *   });     
 * Copyright 2009 LYM [ lym6520@qq.com ] 
 */
(function(idea) {
    idea.extend(idea.fn, {
        jpagebar : function(setting) {
            var pb = idea.extend({
                // pagebar 对象div
                renderTo : idea(document.body),
                // 总页码
                totalpage : 0,
                // 当前页码
                currentPage : 0,
                //每页显示条数
                pageSize:0,
                //总条数
                rowCount:0,
                // 分页条样式
                pagebarCssName : 'pagebar',
                // 页码样式
                pageNumberCssName : 'page_number',
                // 首页、Pre、Next、尾页样式
                pageNameCssName : 'pageName',
                // 选中首页、Pre或Next、尾页样式
                currentPageNameCssName : 'current_pageName',
                // 当前选中页码样式
                currentPageNumberCssName : 'current_page_number',
                // 显示总页码样式
                totalpageNumberCssName : 'totalpage_number',
                // 点击页码action
                onClickPage : function(pageIndex,pageSize) {

                }
            }, setting);

            pb.resetPagebar = function() {
                var _this = this;
                _this.renderTo = (typeof _this.renderTo == 'string' ? idea(_this.renderTo) : _this.renderTo);

                /*
                 * ----------> html : <div> ---->pagebar <a>首页</a> <a>Pre</a> <a>&nbsp;</a>... ----> 显示页码 <a>Next</a> <a>尾页</a> <span class="show_page_detail"> ---->显示信息 <span
                 * class="current_page">currentpage</span> ----> 显示当前页码 <span>/</span> <span class="total_page">totalpage</span> ----> 显示总页码 </span> </div> <-----------
                 */
                var pagebar = _this.renderTo;
                pagebar.attr('class', _this.pagebarCssName);

                // 清空分页导航条
                pagebar.empty();

                var pager='';
                var pageSize=_this.pageSize;
                var rowCount=_this.rowCount;
                pager += "	<div style=\"width:65%;display:inline-block;float:left;padding-left: 50px;\">共"+rowCount+"项，每页显示 <select id=\"pageSize\" name=\"pageSize\"  class=\"input_ff\" style=\"width:80px;\" onchange=\"idea.fn.changeTotal()\">";
        		if(pageSize==10){
        			pager += " <option value=\"10\" selected=\"selected\" > 10 </option>";
        		}else{
        			pager += " <option value=\"10\"  > 10 </option>";
        		}
        		if(pageSize==20){
        			pager += " <option value=\"20\" selected=\"selected\" > 20 </option>";
        		}else{
        			pager += " <option value=\"20\"  > 20 </option>";
        		}
        		if(pageSize==30){
        			pager += " <option value=\"30\" selected=\"selected\" > 30 </option>";
        		}else{
        			pager += " <option value=\"30\"  > 30 </option>";
        		}
        		if(pageSize==50){
        			pager += " <option value=\"50\" selected=\"selected\" > 50 </option>";
        		}else{
        			pager += " <option value=\"50\"  > 50 </option>";
        		}
        		pager += " </select>条记录</div>"; 
        		idea(pager).appendTo(pagebar);
                if (totalpage = 0) {
                    return;
                }
                // 分页
                var front_block = parseInt(_this.currentPage) - 4;// 当前页码前面一截,原来是5
                var back_block = parseInt(_this.currentPage) + 4;// 当前页码后面一截,原来是5

                // 处理首页、Pre
                if (_this.currentPage == 1) {
                    // 当前页为第一页
                    idea('<a>首页</a> ').attr('class', _this.pageNameCssName + ' ' + _this.currentPageNameCssName).appendTo(pagebar);
                    idea('<a>上一页</a> ').attr('class', _this.pageNameCssName + ' ' + _this.currentPageNameCssName).appendTo(pagebar);

                }
                else {
                     //当前页大于第一页
                    idea('<a>首页</a> ').attr('class', _this.pageNameCssName).bind("click", function() {
                        _this.onClickPage(1,pageSize);
                  }).appendTo(pagebar);
                    idea('<a>上一页</a> ').attr('class', _this.pageNameCssName).bind("click", function() {
                        _this.onClickPage(_this.currentPage - 1,pageSize);
                    }).appendTo(pagebar);
                }

                // 处理数字页码

                if (_this.totalpage == 1) {
                    // 共1页
                    idea('<a>1</a> ').attr('class', _this.currentPageNumberCssName).bind("click", function() {
                        _this.onClickPage(1,pageSize);
                    }).appendTo(_this.renderTo);

                }
                else {
                    // 有多页
                    var tempBack_block = _this.totalpage;
                    var tempFront_block = 1;
                    if (back_block < _this.totalpage)
                        tempBack_block = back_block;
                    if (front_block > 1)
                        tempFront_block = front_block;

                    for ( var i = tempFront_block; i <= tempBack_block; i++) {
                        if (_this.currentPage == i) {
                            // 当前页
                            idea('<a>' + i + '</a> ').attr('class', _this.pageNumberCssName + ' ' + _this.currentPageNumberCssName).appendTo(pagebar);
                        }
                        else {
                            idea('<a>' + i + '</a> ').attr('class', _this.pageNumberCssName).bind("click", function() {
                                _this.onClickPage(this.innerHTML,pageSize);
                            }).appendTo(pagebar);

                        }
                    }
                }
            		
                // Next, 尾页 处理
                if (_this.currentPage == _this.totalpage) {
                    // 当前页为最后一页
                    idea('<a>下一页</a> ').attr('class', _this.pageNameCssName + ' ' + _this.currentPageNameCssName).appendTo(pagebar);
                  idea('<a>尾页</a> ').attr('class', _this.pageNameCssName + ' ' + _this.currentPageNameCssName).appendTo(pagebar);
                }
                else {
                    idea('<a>下一页</a> ').attr('class', _this.pageNameCssName).bind("click", function() {
                        _this.onClickPage(parseInt(_this.currentPage) + 1,pageSize);
                    }).appendTo(pagebar);
                    idea('<a>尾页</a> ').attr('class', _this.pageNameCssName).bind("click", function() {
                        _this.onClickPage(_this.totalpage,pageSize);
                   }).appendTo(pagebar);
                }

                 /*红色字体显示当前页
                idea('<span>共'+_this.totalpage+'页</span> ').attr('class',_this.totalpageNumberCssName).appendTo(pagebar);
                 idea('&nbsp;&nbsp;&nbsp;<span>当前'+_this.currentPage+'</span> ').attr('class',_this.currentPageNumberCssName).appendTo(pagebar);
                 idea('<span>/</span>').appendTo(pagebar);
                 idea('<span>共'+_this.totalpage+'页</span> ').attr('class',_this.totalpageNumberCssName).appendTo(pagebar);

                 pagebar.find("a").attr('class',_this.pageNumberCssName);*/
            };
            pb.resetPagebar();
            return pb;
        },
        
        changeTotal : function() {
        	pageNums();
        },
        
        setCurrentPage : function(_this, currentPage,pageSize) {
            _this.currentPage = currentPage;
            _this.pageSize = pageSize;
            _this.resetPagebar(_this);
        },
        getCurrentPage:function(_this) {
            return _this.currentPage;
        },
        setTotalPage : function(_this,currentPage,totalpage,pageSize,rowCount) {
            _this.currentPage = currentPage;
            _this.totalpage = totalpage;
            _this.pageSize = pageSize;
            _this.rowCount = rowCount;
            _this.resetPagebar(_this);
        }

    });
})(jQuery);
