var oBtn = document.getElementById("btn");
      var oBox = document.getElementById("box");
      oBtn.onclick = function() {
        if (oBox.style.display == "none") {
        // 如果层是隐藏的
        oBox.style.display = "block";
        } else {
          // 如果层是显示的
          oBox.style.display = "none";
        }
      };
      oBox.onclick = function() {
        if (oBox.style.display == "block") {
        // 如果层是隐藏的
        oBox.style.display = "none";
        } else {
          // 如果层是显示的
          oBox.style.display = "block";
        }
      }