(function() {
    // Test to see if the browser supports the given applicationType and minimum platform version (example: Adobe Flash version 9,0,115).
    this.HasMinApplicationVersion = function(applicationType, minVersion) { return true; }

    // Based on the browser capabilities, determine what content to render in the container DOM element.
    this.ResolveClientContent = function(contentList) {
        var clientContent = new String("");

        // Get content that best fits the browser's capabilities.
        var len = contentList.length;
        for (var i = 0; i < len; i++) {
            var item = contentList[i];

            if (this.HasMinApplicationVersion(item.applicationType, item.minVersion)) {
                clientContent = item.content;
                break;
            }
        }

        return clientContent;
    }

    // Write the HTML content into the specified container element.
    this.WriteContent = function(elementId, content) {

        if (document.getElementById(elementId)) {
            document.getElementById(elementId).innerHTML = content;
        }
    }

    var contentInfo = {"containerId":"i_video","contentList":[{"applicationType":"application/x-shockwave-flash","minVersion":"9,0,115","content":"\u003cembed name=\"i_6570e39c8f974444946935af140f2d8f\" src=\"http://applications.fliqz.com/ec7350df12af4c5084452e8a2c74bfe4.swf\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"100%\" height=\"100%\" flashvars=\"at=88085122ddbb49ff8541d02664169cef&amp;autoPlayback=true\" bgcolor=\"#000000\" allowscriptaccess=\"always\" allowfullscreen=\"true\" scale=\"exactfit\" wmode=\"window\" menu=\"false\" /\u003e"}]};

    this.WriteContent(contentInfo.containerId, this.ResolveClientContent(contentInfo.contentList));
})();