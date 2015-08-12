
AIMS = {
    
    /**
     * Calculates the weight of the password and updates the UI.
     * @returns {undefined}
     */
    processPasswordSecurity: function () {
        var valueAlphaChar = 4;
        var valueNumericChar = 6;
        var valueOtherChar = 9;

        var score = 0;
        var pwString = $("[id$='d_pw']").val();

        for (var i = 0; i < pwString.length; i++) {
            if (pwString[i].match(/[0-9]/g)) {
                score += valueNumericChar;
            }
            if (pwString[i].match(/[A-Z]|[a-z]/g)) {
                score += valueAlphaChar;
            }
            if (pwString[i].match(/(.*[!,@,#,$,%,^,&,*,?,_,~])/)) {
                score += valueOtherChar;
            }
        }
        if (score === 0) {
            reset();
        }
        if (score <= 30) {
            setLow();
        }
        if (score > 30 & score <= 55) {
            setMedium();
        }
        if (score > 55) {
            setStrong();
        }

        function setLow() {
            $("[id$='weightDesc']").text('weak');
            $("[id$='weightLow']").css("background", "red");
            $("[id$='weightMedium']").css("background", "white");
            $("[id$='weightHigh']").css("background", "white");
        }
        function setMedium() {
            $("[id$='weightDesc']").text('medium');
            $("[id$='weightLow']").css("background", "red");
            $("[id$='weightMedium']").css("background", "yellow");
            $("[id$='weightHigh']").css("background", "white");
        }
        function setStrong() {
            $("[id$='weightDesc']").text('strong');
            $("[id$='weightLow']").css("background", "red");
            $("[id$='weightMedium']").css("background", "yellow");
            $("[id$='weightHigh']").css("background", "green");
        }
        function reset() {
            $("[id$='weightLow']").css("background", "white");
            $("[id$='weightMedium']").css("background", "white");
            $("[id$='weightHigh']").css("background", "white");
        }
    }
};



