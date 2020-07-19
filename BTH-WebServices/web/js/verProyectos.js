function encodeImageFileAsURL() {
                    var filesSelected = document.getElementById("inputFileToLoad").files;
                    if (filesSelected.length > 0) {
                        var fileToLoad = filesSelected[0];
                        var fileReader = new FileReader();
                        fileReader.onload = function (fileLoadedEvent) {
                            var srcData = fileLoadedEvent.target.result; // <--- data: base64
                            var newImage = document.createElement('img');
                            newImage.src = srcData;
                            document.getElementById("imgTest").innerHTML = newImage.outerHTML;
                            //alert("Converted Base64 version is " + document.getElementById("imgTest").innerHTML);
                            //service(document.getElementById("imgTest").innerHTML);
                            console.log("Converted Base64 version is " + document.getElementById("imgTest").innerHTML);
                        };
                        fileReader.readAsDataURL(fileToLoad);
                    }
                }
                function service(base) {
                    var data = {
                        nombre: base
                    };
                    fetch("/bth/api/proyect/recibido", {
                        method: "POST",
                        body: JSON.stringify(data),
                        headers: {
                            'Accept': 'application/json, text/plain',
                            'Content-Type': 'application/json;charset=UTF-8'
                        }
                    })
                            .then(function (request) {
                                return request.json();
                            })
                            .then(function (json) {
                                console.log(json["response"]);
                            });
                }
