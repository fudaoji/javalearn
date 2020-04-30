$(document).ready(function() {
    $.extend({
        getParams: function(key) {
            var aQuery = window.location.href.split("?");
            var aGet = new Array();
            if (aQuery.length > 1) {
                var aParam = aQuery[1].split("&");
                for (var i = 0, aParamLength = aParam.length; i < aParamLength; i++) {
                    var aTemp = aParam[i].split("=");
                    aGet[aTemp[0]] = aTemp[1];
                }
            }
            return aGet.hasOwnProperty(key) ? aGet[key] : aGet;
        },
        isEmpty: function (obj) {
            for (var name in obj) {
                return false;
            }
            return true;
        }
    })
});