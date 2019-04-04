//遍历json实现模糊查询
var CJS = require('./public.js')
var Base = require('./base64.js') 
function swselect(sw,json) {
  if (sw == "" || sw == null) {
    //json = eval(json);  //json是你的json变量名
    return json;
  } else {
    var newJson = [];
   // json = eval(json);
    for (var i = 0; i < json.length; i++) {
      if ((json[i].searchword).indexOf(sw) > -1) {  //name为你需要遍历的变量
        var tempJson = {   //一下id和name是json中需要输出的变量
          "searchword": json[i].searchword,
          "category_pwd": json[i].category_pwd,
          "username": json[i].username,
          "password": json[i].password,
          "remarks": json[i].remarks
        };
        newJson.push(tempJson);
      }
    }
    return newJson;
  }
}
function sw_decode(json) {
  if (json.length == "" || json.length == null) {
    //json = eval(json);  //json是你的json变量名
    return json;
  } else {
    var newJson = [];
    // json = eval(json);
    for (var i = 0; i < json.length; i++) {     
        var tempJson = {   
          "searchword": Base.decode(CJS.Decrypt(json[i].searchword)),
          "category_pwd": json[i].category_pwd,
          "username": json[i].username,
          "password": json[i].password,
          "remarks": json[i].remarks
        };
        newJson.push(tempJson);
      
    }
    return newJson;
  }
}
module.exports = {
  swselect: swselect,
  sw_decode: sw_decode
}