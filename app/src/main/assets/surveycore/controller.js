loadSurvey(json);
function loadSurvey(jsonObject){
    window.survey = new Survey.Model(jsonObject);

    survey
        .onComplete
        .add(function (result) {
            document
                .querySelector('#surveyResult')
                .innerHTML = "result: " + JSON.stringify(result.data);
            JSInterface.sendData(JSON.stringify(result.data));
        });

    $("#surveyElement").Survey({model: survey});
    
}