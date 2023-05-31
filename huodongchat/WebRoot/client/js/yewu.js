/**
 * Created by Bowa on 2014/8/28.
 */
var shoplist = [];
var goodlist = [];
var focusobj = null;
var focushop = null;
var focuspost = null;
var gouwuche = "gouwuche";
var postslist = [];
var _yanzhengma = "";
var focuslist = null;
var chattimmer = null;
var iftobutton = true;
var path = "";
var isRecordStart = false;
var counttimmer = null;
var count = 0;

var _chattype = 1;//1朋友聊天,2群聊天
var _mingancis = null;


$(function(){
//设置类别列表


    var p5 = {};
    p5.tpl = '<li><a href="#" onclick="postDetail(%s);">'+
    '<h2>%s</h2>'+
    '<p>%s</p>'+
    '</a></li>';
    p5.colums = ["id","title","ndate"];
    $("#posts").data("property",JSON.stringify(p5));

    var p6 = {};
    p6.tpl = '<li><a href="#" onclick="">'+
    '<h2>%s</h2>'+
    '<p>%s</p>'+
    '<p>%s</p>'+
    '</a></li>';
    p6.colums = ["ndate","note","username"];
    $("#replays").data("property",JSON.stringify(p6));

    //clickYanzhengma();


    var p4 = {};
    p4.tpl = '<li><a onclick="toQunInfo(%s);">'+
        '<h2>%s</h2>'+
        '<p>%s</p>'+
        '</a><a href="#" onclick="toChat(%s,2);"></a></li>';
    p4.colums = ["id","title","note","id"];
    $("#myqunzulist").data("property",JSON.stringify(p4));


    var pp = {};
    pp.tpl = '<li onclick="toQunInfo(%s);">'+
        '<h2>%s</h2>'+
        '<p>%s</p>'+
        '</li>';
    pp.colums = ["id","title","note"];
    $("#qunlist").data("property",JSON.stringify(pp));
    $("#qunzulist2").data("property",JSON.stringify(pp));

    var ppp = {};
    ppp.tpl = '<li>'+
        '<p>%s</p>'+
        '<p onclick="toUserInfo(%s)" style="color: blue;">%s</p>'+
        '<p>%s</p>'+
        //'<p onclick="zan(this,%s);" style="color: red;"><span>%s</span>赞</p>'+
        '</li>';
    ppp.colums = ["note","uid","username","ndate"];
    $("#qunmsglist").data("property",JSON.stringify(ppp));


    var pppp = {};
    pppp.tpl = '<li>'+
        '<p>%s</p>'+
        '<p>%s</p>'+
        '<p>%s</p>'+
        '</li>';
    pppp.colums = ["note","username","ndate"];
    $("#msglist").data("property",JSON.stringify(pppp));






    var fp = {};
    fp.tpl = '<li><a  onclick="toUserInfo(%s);">'+
        '<img src="'+fileurl+'%s">'+
        '<h2>%s</h2>'+
        '<p>%s</p>'+
        '</a><a href="#" onclick="toChat(%s,1)"></a></li>';
    fp.colums = ["id","img","username","sex","id"];
    $("#myfriendlist").data("property",JSON.stringify(fp));


    var fpp = {};
    fpp.tpl = '<li onclick="toUserInfo(%s);">'+
        '<img src="'+fileurl+'%s">'+
        '<h2>%s</h2>'+
        '<p>%s</p>'+
        '</li>';
    fpp.colums = ["id","img","username","sex"];
    $("#userlist").data("property",JSON.stringify(fpp));

    var fppp = {};
    fppp.tpl = '<li onclick="toYzMessage(%s);">'+
        '<img src="'+fileurl+'%s">'+
        '<h2>%s</h2>'+
        '<p>%s</p>'+
        '</li>';
    fppp.colums = ["id","img","fuser","ndate"];
    $("#messagelist").data("property",JSON.stringify(fppp));


    var atpl = {};
    atpl.tpl = '<li><a href="#" onclick="toAddressMg(%s);">'+
        '<h2>%s</h2>'+
        '</a><a onclick="delAddress(%s);"></a></li>';
    atpl.colums = ["id","title","id"];
    $("#addresss").data("property",JSON.stringify(atpl));


    $("#filebutton").unbind("click").bind("click",function(){
        sendFile();
    });


    $("#recordbtn").unbind("click").bind("click",function(){
        if(isRecordStart){
            isRecordStart = false;
            hideLoader();
            $("#recordbtn").text("点击录音");
            path = myObj.getRecordFile();
            setTimeout(function(){
                sendMsg(path,2);
            },2000);
            clearInterval(counttimmer);
            count = 0;
            myObj.stopSound();
        }else{
            isRecordStart = true;
            $("#recordbtn").text("录音中");
            //showLoader("录音中 "+count+"s",true);
            counttimmer = setInterval(function(){
                //showLoader("录音中 "+count+"s",true);
                $("#recordbtn").text("录音中"+count+"s");
                count++;
            },1000);
            myObj.recordSound();
        }

    });

    ajaxCallback("getTypes",{},function(data){
        _mingancis = data;
    });

});


/*****************************************************************好友管理********************************************/
function toMyFriend(){
    changePage('friendpage');
    listMyFriend();
}
function listMyFriend(){
    ajaxCallback("listMyFriend",{uid:userinfo.id},function(data){
        focuslist = data;
        $("#myfriendlist").refreshShowListView(data);
    });
}

function toUserInfo(id){
    ajaxCallback("getUser",{id:id},function(obj){

        if(obj){
            focusobj = obj;
            changePage('userdetailpage');
            checkIsMyFriend();
            $("#vusername2").text("用户名:"+focusobj.username);
            $("#vuserimg2").attr("src",fileurl+focusobj.img);
            $("#vbirth2").text("生日:"+focusobj.birth);
            $("#vsex2").text("性别:"+focusobj.sex);
            $("#vqq2").text("QQ:"+focusobj.qq);
        }
    });


}

function toSearchFriend(){
    changePage("addfriendpage");
    listUser();
}

function listUser(){
    ajaxCallback("listUser",{},function(data){
        focuslist = data;
        $("#userlist").refreshShowListView(data);
    });
}

function checkIsMyFriend(cb){
    ajaxCallback("checkIsMyFriend",{uid:userinfo.id,fid:focusobj.id},function(data){
        //cb && cb(data.info);
        if(data.info=="1"){
            $("#canadd").hide();
            $("#candelf").show();
        }else{
            if(focusobj.id!=userinfo.id){
                $("#canadd").show();
                $("#candelf").hide();
            }else{
                $("#canadd").hide();
                $("#candelf").hide();
            }

        }
    });
}

function addFriend(){
    /*ajaxCallback("addFriend",{uid:userinfo.id,fid:focusobj.id},function(data){
     userinfo = data;
     toMyFriend();
     });*/
    //改为同意的时候后台自动加上
    ajaxCallback("sendAddMessage",{fid:userinfo.id,tid:focusobj.id,status:"待同意",fuser:userinfo.username,img:userinfo.img},function(data){
        showLoader("请求已发送,等待好友验证!",true);
    });
}

function delFriend(){
    ajaxCallback("delFriend",{uid:userinfo.id,fid:focusobj.id},function(data){
        userinfo = data;
        toMyFriend();
    });
}


function toMyYanzhengMessage(){
    changePage('yzmessagelistpage');
    ajaxCallback("listMyAddMessage",{uid:userinfo.id},function(data){
        focuslist = data;
        $("#messagelist").refreshShowListView(data);
    });
}

function toYzMessage(id){
    var msg = getObjectById(id,focuslist);
    if(msg){
        focusobj = msg;
        changePage('yzmsgdetailpage');
        $("#vusername4").text(msg.fuser);
        $("#vstatus").text("状态:"+msg.status);
        $("#userimg").attr("src",fileurl+msg.img);
        if(msg.status="待同意"){
            $("#opctn").show();
        }else{
            $("#opctn").hide();
        }
    }
}

function agree(){
    ajaxCallback("addFriend",{uid:focusobj.fid,fid:focusobj.tid},function(r){
        ajaxCallback("addFriend",{uid:userinfo.id,fid:focusobj.fid},function(rr){
            ajaxCallback("updateYzMsgStatus",{id:focusobj.id,status:"已同意"},function(data){
                userinfo = rr;
                toMyFriend();
            });

        });
    });
}

function refuse(){
    ajaxCallback("updateYzMsgStatus",{id:focusobj.id,status:"已拒绝"},function(data){
        goback();
    });

}




var addresslist = [];

/********************************************************计划增删改******************************************/
function toAddressPage(){
    changePage("addresspage");
    listAddress();
}
function listAddress(){
    ajaxCallback("listAddress",{uid:userinfo.id},function(data){
        addresslist = data;
        $("#addresss").refreshShowListView(data);
    });
}
function toAddressMg(id){
    changePage("addressmgpage");
    if(id){
        $("#addressid").val(id);
        $("#addressaction").val("edit");
        var obj = getObjectById(id,addresslist);
        focusobj = obj;
        $("#title").val(obj.title);
        $("#note").val(obj.note);
    }else{
        $("#addressform")[0].reset();
        $("#addressaction").val("add");
    }

}





function saveAddress(){
    var fdata = serializeObject($("#addressform"));
    fdata.uid = userinfo.id;
    ajaxCallback("saveAddress",fdata,function(){
        showTipTimer("操作成功",function(){
            toAddressPage();
        });
    });
}

function addAddress(){
    var fdata = {};
    fdata.uid = userinfo.id;
    fdata.title = userinfo.address;
    fdata.action = "add";
    ajaxCallback("saveAddress",fdata,function(){

    });
}


function delAddress(id){
    ajaxCallback("delAddress",{id:id},function(){
        toAddressPage();
        showLoader("操作成功",true);
    });
}


/*****************************************************************好友管理结束********************************************/



function toMain(){
    toLuntan();
}



function backclear(){
    clearInterval(chattimmer);
    goback();
}

function toLuntan(id){
    changePage("luntanpage");
    listPosts(id);
}
function listPosts(id){
    ajaxCallback("listPosts",{uid:id},function(data){
        postslist = data;
        $("#posts").refreshShowListView(data);
    });
}
function toAddForm(){
    changePage("addformpage");
}
function addForm(){
    var note = $("#fnote").val();
    var title = $("#ftitle").val();
    uplaodImg(function(r){
        ajaxCallback("addPosts",{uid:userinfo.id,title:title,note:note,username:userinfo.username,img:r},function(){
            toLuntan();
        });
    });

}
function postDetail(id){
    var obj = getPostsById(id);
    focuspost = obj;
    changePage("postdetail");
    $("#vptitle").text("标题:"+obj.title);
    $("#vpnote").text("内容:"+obj.note);
    $("#vpusername").text("发布者:"+obj.username);
    $("#vpdate").text("时间:"+obj.ndate);
    if(obj.img){
        $("#pimg").attr("src",fileurl+obj.img);
        $("#imgctn").show();
    }else{
        $("#imgctn").hide();
    }
    if(obj.uid == userinfo.id){
        $("#mypost").show();
    }else{
        $("#mypost").hide();
    }
    listReplay();
}
function listReplay(){
    ajaxCallback("listReplay",{pid:focuspost.id},function(data){
        $("#replays").refreshShowListView(data);
    });
}
function addReplay(){
    var note = $("#rnote").val();
    ajaxCallback("addReplay",{pid:focuspost.id,uid:userinfo.id,username:userinfo.username,note:note},function(data){
        listReplay();
        $("#rnote").val("");
    });
}
function getPostsById(id){
    for(var i=0;i<postslist.length;i++){
        if(postslist[i].id == id){
            return postslist[i];
        }
    }
}



function mypost(){
    toLuntan(userinfo.id);
}


function delPosts(){
    ajaxCallback("delPosts",{id:focuspost.id},function(data){
        toLuntan();
    });
}

function toQunzu(){
    changePage("qunpage");
    listMyQunzu();
    $("#myqunzulist").show();
    $("#qunzulist2").hide();
}

function listMyQunzu(){
    ajaxCallback("listMyQunzu",{uid:userinfo.id},function(data){
        focuslist = data;
        $("#myqunzulist").refreshShowListView(data);
    });
}

function saveQunzu(){
    var fdata = serializeObject($("#qunzuform"));
    fdata.username = userinfo.username;
    fdata.uid = userinfo.id;
    ajaxCallback("saveQunzu",fdata,function(data){
        ajaxCallback("addQunzu",{uid:userinfo.id,qid:data.id},function(data){
            toQunzu();
        });

    });
}

function toQunzuList(){
    $("#myqunzulist").hide();
    $("#qunzulist2").show();
    ajaxCallback("listQunzu",{},function(data){
        focuslist = data;
        $("#qunzulist2").refreshShowListView(data);
    });
}

function toSearchQunzu(){
    changePage("addQunzupage");
    listQun();
}


function refreshQun(el){
    var type = $(el).val();
    listQun(type);
}

function listQun(type){
    type = type || "";
    ajaxCallback("listQunzu",{type:type},function(data){
        focuslist = data;
        $("#qunlist").refreshShowListView(data);
    });
}

function addQunzu(){
    ajaxCallback("addQunzu",{uid:userinfo.id,qid:focusobj.id},function(data){
        userinfo = data;
        showTipTimer("操作成功!",function (){
            toQunzu();
        })
     });

}

function delQunzus(){
    ajaxCallback("delQunzus",{uid:userinfo.id,qid:focusobj.id},function(data){
        userinfo = data;
        toQunzu();
    });
}

function toAddQunzu(){
    changePage('qunzumgpage');
}

function toQunInfo(id){
    var obj = getObjectById(id,focuslist);
    if(obj){
        focusobj = obj;
        changePage('qundetailpage');
        checkIsMyQunzu();
        $("#quntitle").text("名称:"+focusobj.title);
        $("#qundesc").text("说明:"+focusobj.note);
    }

}

function checkIsMyQunzu(cb){
    ajaxCallback("checkIsMyQunzu",{uid:userinfo.id,qid:focusobj.id},function(data){
        //cb && cb(data.info);
        if(data.info=="1"){
            $("#canbuy").hide();
            $("#candel").show();
        }else{
            if(focusobj.id!=userinfo.id){
                $("#canbuy").show();
                $("#candel").hide();
            }else{
                $("#canbuy").hide();
                $("#candel").hide();
                $("#candaohang").hide();
            }

        }
    });
}


function sendQunMsg(){
    var note = $("#chatnote").val();
    var msg = {};
    msg.note = note;
    msg.qid = focusobj.id;
    msg.uid = userinfo.id;
    msg.username = userinfo.username;
    ajaxCallback("addQunMessage",msg,function(data){
        ajaxCallback("listQunMessage",{uid:userinfo.id,qid:focusobj.id},function(data){
            $("#chatnote").val("");
            $("#qunmsglist").refreshShowListView(data);
        });
    });
}

/*function sendMsg(){
    var note = $("#chatcontent").val();
    var msg = {};
    msg.note = note;
    msg.fid = focusobj.id;
    msg.uid = userinfo.id;
    msg.username = userinfo.username;
    ajaxCallback("addMessage",msg,function(data){
        ajaxCallback("listMyMessage",{uid:userinfo.id,fid:focusobj.id},function(data){
            $("#chatcontent").val("");
            $("#msglist").refreshShowListView(data);
        });
    });
}*/
var refreshgap = 5000;
function toQunChat(id){
    var qz = getObjectById(id,focuslist);
    focusobj = qz;
    changePage("chatpage");
    chattimmer = setInterval(function (){
        refreshServerQunChatList();
    },refreshgap);
}

function toChat(id,type){
    if(userinfo.status=="禁言"){
        showLoader("你已被禁言",true);
        return;
    }
    _chattype = type;
    var qz = getObjectById(id,focuslist);
    focusobj = qz;
    changePage("chatpage");
    refreshServerChatList();
    chattimmer = setInterval(function (){
        refreshServerChatList();
    },refreshgap);
}

function refreshChatMSg(){
    ajaxCallback("listMyMessage",{uid:userinfo.id,fid:focusobj.id},function(data){
        $("#msglist").refreshShowListView(data);
    },true);
}

function zan(el,id){
    ajaxCallback("zan",{id:id},function(data){
        var showel = $(el).find("span").eq(0);
        $(showel).text(data.info);
    });
}







function sendFile(){
    getFileAttach(function(path){
        sendMsg(path,3);
    });
}

function sendMsg(path,type){

    var ajaxurl = "addMessage";
    if(_chattype==2){
        ajaxurl="addQunMessage";
    }
    var obj = {};
    obj.uid = userinfo.id;
    obj.fid = focusobj.id;
    if(_chattype==2){
        obj.qid = focusobj.id;
    }
    obj.note = $("#msgaarea").val();
    obj.username = userinfo.username;
    obj.fusername = focusobj.username;
    obj.type = 1;
    obj.img = userinfo.img;
    if(path){
        if(type==2){
            obj.type = type;
            uploadFileUrl = path;
            uplaodImg(function(r){
                obj.attach = r;
                ajaxCallback(ajaxurl,obj,function(data){
                    iftobutton = true;
                    refreshServerChatList();
                });
            });
        }else if(type==3){
            obj.type = type;
            uploadFileUrl = path;
            uplaodImg(function(r){
                obj.attach = r;
                obj.attachname = uploadFileUrl.substr(uploadFileUrl.lastIndexOf("/")+1);
                ajaxCallback(ajaxurl,obj,function(data){
                    iftobutton = true;
                    refreshServerChatList();
                });
            });
        }

    }else{
        if(checkMgc(obj.note)){
            showLoader("消息不合法",true);
            return;
        }
        obj.attach = "";
        ajaxCallback(ajaxurl,obj,function(data){
            iftobutton = true;
            refreshServerChatList();
        });
    }
    $("#msgaarea").val("");


    /*var note = $("#chatnote").val();
    var msg = {};
    msg.note = note;
    msg.qid = focusobj.id;
    msg.uid = userinfo.id;
    msg.username = userinfo.username;
    ajaxCallback("addQunMessage",msg,function(data){
        ajaxCallback("listQunMessage",{uid:userinfo.id,qid:focusobj.id},function(data){
            $("#chatnote").val("");
            $("#qunmsglist").refreshShowListView(data);
        });
    });*/
}

function checkMgc(str){
    for(var i=0;i<_mingancis.length;i++){
        var mgc = _mingancis[i]['title'];
        if(str.indexOf(mgc)!=-1){
            return true;
        }
    }
    return false;
}

function refreshServerChatList(){
    var queryobj = {uid:userinfo.id,fid:focusobj.id};
    var ajaxurl = "listMyMessage";
    if(_chattype==2){
        queryobj = {uid:userinfo.id,qid:focusobj.id};
        ajaxurl = "listQunMessage";
    }
    ajaxCallback(ajaxurl,queryobj,function(data){
        refreshChatList(data);
    },true);
}


var _oldlength = null;
function refreshChatList(data){
    if(data.length!=_oldlength){
        iftobutton = true;
        _oldlength = data.length;
    }
    var html = "";
    if(data && data.length){
        for(var i=0;i<data.length;i++){
            var msg = data[i];
            var cn = "citemto";
            if(msg.uid != userinfo.id){
                var cn = "citemfrom";
            }
            var li = "";
            if(msg.type==1){
                li = '<li class="'+cn+'"><div><img src="'+fileurl+msg.img+'"><p>'+msg.note+'</p></div></li>';
            }else if(msg.type==2){
                li = '<li class="'+cn+'" onclick="myObj.playAudio(\''+msg.attach+'\');"><div><img src="'+fileurl+msg.img+'"><p>点击播放语音</p></div></li>';
            }else if(msg.type==3){
                if(getFileType(msg.attach)==".png" || getFileType(msg.attach)==".jpg"||getFileType(msg.attach)==".gif"||getFileType(msg.attach)==".jpeg"){
                    li = '<li class="'+cn+'" onclick="localFile(\''+msg.attach+'\');"><div><img src="'+fileurl+msg.img+'"><p class="imgctn" style="background-image: url('+fileurl+msg.attach+')"></p></div></li>';
                }else{
                    li = '<li class="'+cn+'" onclick="localFile(\''+msg.attach+'\');"><div><img src="'+fileurl+msg.img+'"><p>'+msg.attachname+'</p></div></li>';
                }

            }

            html+=li;
        }
        $("#chatlist").html(html);
        setScroll("chatscroll",document.getElementById("chatdiv"));
    }else{
        $("#chatlist").html(html);
    }
    var hei = $("#chatlist").height();
    if(iftobutton){
        if(hei>600){
            var scrollY = 0-(hei-600);
            scrolls["chatscroll"].scrollTo(0,scrollY,300);
        }
        iftobutton = false;
    }


}