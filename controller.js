var json = {
    questions: [
        {
            name: "name",
            type: "text",
            title: "Please enter your name:",
            placeHolder: "Jon Snow",
            isRequired: true
        }, {
            name: "birthdate",
            type: "text",
            inputType: "date",
            title: "Your birthdate:",
            isRequired: true
        }, {
            name: "color",
            type: "text",
            inputType: "color",
            title: "Your favorite color:"
        }, {
            name: "email",
            type: "text",
            inputType: "email",
            title: "Your e-mail:",
            placeHolder: "jon.snow@nightwatch.org",
            isRequired: true,
            validators: [
                {
                    type: "email"
                }
            ]
        }
    ]
};
console.log(window.location)
var url = new URL(window.location);
var body = url.searchParams.get("body");
console.log(body);

if(body != undefined && body != 'undefined'){
    loadSurvey(body)
}
function loadSurvey(jsonObject){
    var json = JSON.parse(jsonObject) 
    window.survey = new Survey.Model(json);

    survey
        .onComplete
        .add(function (result) {
            document
                .querySelector('#surveyResult')
                .innerHTML = "result: " + JSON.stringify(result.data);
        });

    $("#surveyElement").Survey({model: survey});
    
}