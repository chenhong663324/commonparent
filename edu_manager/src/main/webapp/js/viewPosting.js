/**
 * 无限滚动对象tasks
 */
var tasks;
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

var $end=$('#end');


requestPostingByPosting_id(getQueryVariable("posting_id"));

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


function requestPostingByPosting_id(id) {
    var container = $(document).find(".col-md-12");
    var html;
    $.ajax({
        type:"post",
        dataType:"json",
        contentType: "application/json;charset=UTF-8",
        url:"/Roast/main/queryPostingByID.action?posting_id="+id,
        success:function(result) {
            //alert("replies:"+JSON.stringify(result));
            html = template(result);
            //var con=document.getElementById("repliesContainer");
            container[0].insertAdjacentHTML("BeforeEnd",html);
            //alert(html);
        }
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
