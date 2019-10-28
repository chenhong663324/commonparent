function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function getDate()
{
    var d,s;
    d = new Date();
    s = d.getFullYear() + "-";             //取年份
    s = s + (d.getMonth() + 1) + "-";//取月份
    s += d.getDate() + " ";         //取日期
    s += d.getHours() + ":";       //取小时
    s += d.getMinutes() + ":";    //取分
    s += d.getSeconds();         //取秒

    return(s);

}