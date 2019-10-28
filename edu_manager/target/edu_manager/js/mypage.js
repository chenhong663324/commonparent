/**
 * 无限滚动对象tasks
 */
var tasks;
var hasLoadPage=0;
var pageinfo=null;
//模板和已编译的模板对象
var itemTemplateSrc = $("#postings_template").html();
//alert(itemTemplateSrc);
var template = Handlebars.compile(itemTemplateSrc);
var replyForPostings_templateSrc   = $("#replyForPostings_template").html();
//alert(source);
var replyForPostings_template = Handlebars.compile(replyForPostings_templateSrc);

var replyForReply_templateSrc=$("#replyForReply_template").html();

var replyForReply_template=Handlebars.compile(replyForReply_templateSrc);

var OneReply_templateSrc=$("#OneReply_template").html();
var  OneReply_template=Handlebars.compile(OneReply_templateSrc);

var personal_templateSrc=$("#personal_template").html();
var  personal_template=Handlebars.compile(personal_templateSrc);



var $wait=$("#wait");
var $end=$('#end');
var $error=$('#error');
var $loadMoreButton=$("#loadMore");

function queryPostingsByName(button){
    var name=$(document).find('#posting_name').val();
    var con=$(document).find('.tasks')[0];
    var html;
    $.ajax({
        type:"post",
        dataType:"json",
        contentType: "application/json;charset=UTF-8",
        url:"/Roast/main/queryPostingsByName.action?name="+name,
        success:function(result) {
            con.innerHTML="";
            html = template(result);
            con.insertAdjacentHTML("AfterBegin",html);
        }
    });
}

$(document).ready(function (){

    tasks=$('.tasks').infiniteScroll({
        // options
        path: getPath,
        //append: '.post',
        history: false,
        scrollThreshold: false,
        status: '.page-load-status',
        // elementScroll: true,
        // loadOnScroll: true,
        button:'.loadMoreButton',
        responseType: 'text',
        debug:true
    });
    //alert(document.getElementsByClassName('.loadMoreButton')[0]);
    $('.loadMoreButton').on( 'click', function() {
        tasks.infiniteScroll('loadNextPage');
    });

    //数据加载完成但尚未添加时的事件
    tasks.on( 'load.infiniteScroll', function( event, response ) {
        //alert(response);
        pageinfo=JSON.parse(response);
        var postingsList=pageinfo.list;
        var html = template(postingsList);
        tasks.infiniteScroll( 'appendItems', $(html) );
        // do something with JSON...
    });
    /**
     * 请求前的准备工作
     */
    tasks.on( 'request.infiniteScroll', function( event, path ) {
        console.log( 'Loading page: ' + path );
        //显示加载动画
        $wait.css({
                "visibility":"visible",
                "display":"inline"
            }
        );
    });
    /**
     * 添加完成后的工作
     */
    tasks.on( 'append.infiniteScroll', function( event, response, path, items ) {
        //隐藏元素
        $wait.css({
                "visibility":"hidden",
                "display":"none"
            }
        );
    });
    /**
     * 出错时的动作
     */
    tasks.on( 'error.infiniteScroll', function( event, error, path ) {
        $error.css({
                "visibility":"visible",
                "display":"inline"
            }
        );
        $wait.css({
                "visibility":"hidden",
                "display":"none"
            }
        );
    });
    /**
     * 最后一页的动作
     */
    tasks.on( 'last.infiniteScroll', function( event, response, path ) {
        $wait.css({
                "visibility":"hidden",
                "display":"none"
            }
        );
        $end.css({
                "visibility":"visible",
                "display":"inline"
            }
        );
        $loadMoreButton.css({
                "visibility":"hidden",
                "display":"none"
            }
        );
    });

    tasks.infiniteScroll('loadNextPage');

    requestPesonal();
});
// var data = JSON.parse( response );
// //alert(response);
// //alert(list);
// //var document='<div class="jumbotron well-sm well posting post"><h2 class="posting_name">'+data.name+'</h2><p class="posting_content">'+data.introduction+'</p><p><a class="" href="#">Learn more<span class="glyphicon glyphicon-chevron-down"></span>  <!--  btn btn-primary btn-large --></a></p></div>';
// var itemsHTML = data.map( getItemHTML ).join('');
// // convert HTML string into elements
// var $items =  $( itemsHTML );
// //alert(itemsHTML);
// // append item elements
// //tasks.infiniteScroll( 'appendItems', $items );


//alert(itemTemplateSrc);
function getItemHTML( data ) {
    return microTemplate( itemTemplateSrc, data );
}

function microTemplate( src, data ) {
    // replace {{tags}} in source
    return src.replace( /\{\{([\w\-_\.]+)\}\}/gi, function( match, key ) {
        // walk through objects to get value
        console.log("key="+key);
        var value = data;
        key.split('.').forEach( function( part ) {
            value = value[part];
        });
        return value;
    });
}

function getPath(){
    if(pageinfo==null){
        return 'main/queryAllAndReturnPageInfo.action';
    }else{
        if(pageinfo.isLastPage==true){
            //return "main/returnNullList.action";
        }else {
            return "main/queryAllAndReturnPageInfo.action?pn="+pageinfo.nextPage;
        }
    }
}

function requestRepliesByPosting_id(button) {
    var rootHtmlElement=$(button).parents(".posting-template")[0];
    var replylies=$(rootHtmlElement).find(".replylies")[0];
    var divFor_Edit_reply=$(rootHtmlElement).find(".divFor_Edit_reply")[0];

    var id=$(button).attr("data-posting_id");
    var html;

    var medias=$(rootHtmlElement).find(".media");
    var childSpanText=$(button).find(".mytext");

    if($(childSpanText[0]).text()=='收起回复'){
        replylies.innerHTML="";
        // $(replylies).css({
        //         "visibility":"hidden",
        //         "display":"none"
        //     }
        // );
        $(childSpanText[0]).text("查看回复");
    }else if($(childSpanText[0]).text()=='查看回复'){
        if(medias.length>0){
            $(replylies).css({
                    "visibility":"visible",
                    "display":"inline"
                }
            );
            $(childSpanText[0]).text("收起回复");
        }
        else {
            $.ajax({
                type:"post",
                dataType:"json",
                contentType: "application/json;charset=UTF-8",
                url:"/Roast/main/loadReplyByPostingID.action?posting_id="+id,
                success:function(result) {
                    //alert("replies:"+JSON.stringify(result));
                    html = replyForPostings_template(result);
                    //var con=document.getElementById("repliesContainer");
                    replylies.insertAdjacentHTML("BeforeEnd",html);
                    $(childSpanText[0]).text("收起回复");
                    //alert(html);
                }
            });
        }
    }

}

function requestPesonal() {
    var personal=$(document).find(".col-md-3")[0];
    var logout=document.getElementById("u");

    var id=getQueryVariable("id");

    $.ajax({
        type:"post",
        dataType:"json",
        contentType: "application/json;charset=UTF-8",
        url:"/Roast/user/loadPersonalInfo.action?id="+id,
        success:function(result) {
            html = personal_template(result);
            personal.insertAdjacentHTML("AfterBegin",html);
            //alert();
            $(logout).text(result.username);
        }
    });
}

function toPosting(button) {
    var id =$(button).attr("data-posting_id");
    window.location.href="/Roast/main/goToViewPosting.action?posting_id="+id;
}

function onclickToViewSecondReply(button) {
    var rootHtmlElement=$(button).parents(".media")[0];
    var replylies=$(rootHtmlElement).find(".childReplies")[0];

    var id=$(button).attr("data-replyid");
    var html;

    var medias=$(rootHtmlElement).find(".media")[0];
    var childSpanText=$(button).find(".mytext");

    if($(childSpanText[0]).text()=='收起回复'){
        replylies.innerHTML="";
        // $($parent).css({
        //         "visibility":"hidden",
        //         "display":"none"
        //     }
        // );
        $(childSpanText[0]).text("查看回复");
    }else if($(childSpanText[0]).text()=='查看回复'){

            $.ajax({
                type:"post",
                dataType:"json",
                contentType: "application/json;charset=UTF-8",
                url:"/Roast/main/loadReply.action",
                success:function(result) {
                    //alert("replies:"+JSON.stringify(result));
                    html = replyForReply_template(result);
                    //var con=document.getElementById("repliesContainer");
                    replylies.insertAdjacentHTML("BeforeEnd",html);
                    $(childSpanText[0]).text("收起回复");
                }
            });

    }

}
function requestRepliesByReply_id2(button) {
    var rootHtmlElement=$(button).parents(".posting-template")[0];
    var replylies=$(rootHtmlElement).find(".childReplies")[0];
    var divFor_Edit_reply=$(rootHtmlElement).find(".divFor_Edit_reply")[0];

    var id=$(button).attr("data-replyid");
    var html;
    var childSpanText=$(button).find(".mytext");
    replylies.innerHTML="";

    $.ajax({
                type:"post",
                dataType:"json",
                contentType: "application/json;charset=UTF-8",
                url:"/Roast/main/loadReply.action",
                success:function(result) {
                    //alert("replies:"+JSON.stringify(result));
                    html = replyForReply_template(result);
                    //var con=document.getElementById("repliesContainer");
                    replylies.insertAdjacentHTML("BeforeEnd",html);
                    $(childSpanText[0]).text("收起回复");
                }
            });

}
/*
    隐藏或者显示回复元素
    button：需要操作的元素
    level区分回复等级
     */
function showRelyEditor(button,level) {
    if(level==1){
        var rootHtmlElement=$(button).parents(".posting-template")[0];
        var divFor_Edit_reply=$(rootHtmlElement).find(".divFor_Edit_reply")[0];
        var $divFor_Edit_reply=$(divFor_Edit_reply);
    }else if(level==2){
        var rootHtmlElement=$(button).parents(".media")[0];
        var divFor_Edit_reply=$(rootHtmlElement).find(".divFor_Edit_reply")[0];
        var $divFor_Edit_reply=$(divFor_Edit_reply);
    }


    var v=$divFor_Edit_reply.css("visibility");
    //alert(preElement.nextElementSibling);
    if(v=="hidden"){
        $divFor_Edit_reply.css({
                "visibility":"visible",
                "display":"inline"
            }
        );
    }else{
        $divFor_Edit_reply.css({
                "visibility":"hidden",
                "display":"none"
            }
        );
    }
}



function submitRelpy(button) {
    var rootHtmlElement=$(button).parents(".posting-template")[0];

    var requestButton=$(rootHtmlElement).find('.submitSecondRelpy')[0];
    alert(requestButton);
    /**
     * inputValue : 输入的回复值
     * @type {*|jQuery}
     */
    var input=$(button.parentNode).find(".myInput");
    var inputValue=$(input).val();
    //alert($(input).val());
    /**
     * $parent: 获取回复的目标reply，以获取其id
     * @type {*|jQuery}
     */
    var $parent=$(button).parents(".media");
    var target_reply_id =$parent[0].id;

    var posting_id=$(button).parents(".posting-template")[0].id;

    var time=getDate();

    var target_user_id=$(button).attr("data-owner_id");

    $.ajax({
        type:"post",
        dataType:"json",
        contentType: "application/json;charset=UTF-8",
        url:"/Roast/main/addReply.action",
        data:JSON.stringify({"id":"","owner_id":1,"context":inputValue,"introduce":inputValue,
            "time":"2019-06-03 20:12:12",
            "posting_id":posting_id,
            "target_user":parseInt(target_user_id),
            "target_reply":target_reply_id
        }),
        success:function(result) {
            requestRepliesByReply_id2(requestButton);
        }
    });
}

function cleanAndThenloadAllFirstLevelReplies(button){
    var rootHtmlElement=$(button).parents(".posting-template")[0];
    var replylies=$(rootHtmlElement).find(".replylies")[0];
    var divFor_Edit_reply=$(rootHtmlElement).find(".divFor_Edit_reply")[0];

    var id=$(button).attr("data-posting_id");
    alert(id);
    var html;
    var childSpanText=$(button).find(".mytext");
    replylies.innerHTML="";

            $.ajax({
                type:"post",
                dataType:"json",
                contentType: "application/json;charset=UTF-8",
                url:"/Roast/main/loadReplyByPostingID.action?posting_id="+id,
                success:function(result) {
                    //alert("replies:"+JSON.stringify(result));
                    html = replyForPostings_template(result);
                    //var con=document.getElementById("repliesContainer");
                    replylies.insertAdjacentHTML("BeforeEnd",html);
                    $(childSpanText[0]).text("收起回复");
                    //alert(html);
                }
            });

}

function submitFirstLevelRelpy(button) {
    var rootHtmlElement=$(button).parents(".posting-template")[0];
    //var id=$(button).attr("data-replyid");
    var requestButton=$(rootHtmlElement).find('.viewFirstLevelReplies')[0];
    alert(requestButton);
    /**
     * inputValue : 输入的回复值
     * @type {*|jQuery}
     */
    var input=$(button.parentNode).find(".myInput");
    var inputValue=$(input).val();
    //alert($(input).val());
    /**
     * $parent: 获取回复的目标reply，以获取其id
     * @type {*|jQuery}
     */
    var target_reply_id =$(button).parents(".posting-template")[0].id;

    var posting_id=$(button).parents(".posting-template")[0].id;

    var time=getDate();

    var target_user_id=$(button).attr("data-owner_id");

    $.ajax({
        type:"post",
        dataType:"json",
        contentType: "application/json;charset=UTF-8",
        url:"/Roast/main/addReply.action",
        data:JSON.stringify({"id":"","owner_id":1,"context":inputValue,"introduce":inputValue,
            // "time":time,
            "posting_id":posting_id,
            "target_user":parseInt(target_user_id),
            "target_reply":target_reply_id
        }),
        success:function(result) {
            cleanAndThenloadAllFirstLevelReplies(requestButton);
            //requestRepliesByReply_id(requestButton);
            // alert("replies:"+JSON.stringify(result));
            //  html   = OneReply_template(result);
            //  parent.insertAdjacentHTML("BeforeEnd",html);
            //html   = template(context);

        }
    });
}
