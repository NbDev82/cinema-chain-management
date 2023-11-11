
document.getElementById("citySelectConfig").onchange = function() {
    document.getElementById("searchForm").submit();
};

document.getElementById("theaterSelectConfig").onchange = function() {
    document.getElementById("searchForm").submit();
};

document.getElementById("movieTimeConfig").onchange = function() {
    document.getElementById("searchForm").submit();
};

addAllCityOption()
function addAllCityOption(){
    var citySelect = document.getElementById("citySelectConfig");
    var options = Array.from(citySelect.options).map(function (option) {
        return option.value;
    });
    if (options.indexOf("Cả nước") === -1) {
        var option = document.createElement("option");
        option.value = "Cả nước";
        option.text = "Cả nước";
        citySelect.add(option);
    }
}

addAllTheatersOption()
function addAllTheatersOption(){
    var theaterSelect = document.getElementById("theaterSelectConfig");
    var options = Array.from(theaterSelect.options).map(function (option) {
        return option.value;
    });
    if (options.indexOf("Tất cả rạp") === -1) {
        var option = document.createElement("option");
        option.value = "Tất cả rạp";
        option.text = "Tất cả rạp";
        theaterSelect.add(option);
    }
}