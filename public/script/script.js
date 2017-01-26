var myApp = angular.module("myModule",[]);

//Login Controller
    myApp.controller("loginController",function($scope,$http){

        $scope.result = "";

        $scope.login = function(uname, pass){
            $http.get("http://127.0.0.1:8888/login/"+uname+"/"+pass).success(function(data){

                if(data!="" && data[0].current.password == pass)
                {
                    //alert("Login Successfull");
                    $scope.pass = "";
                    $scope.result = "Login Successfull";
                    window.localStorage.setItem('username',JSON.stringify(uname));
                    window.localStorage.setItem('uid',JSON.stringify(data[0].current.id));
                    window.localStorage.setItem('name',JSON.stringify(data[0].current.firstName+" "+data[0].current.lastName));
                    //fetch localstorage
                    //var username = JSON.parse( window.localStorage.getItem('username'));
                    //var uid = JSON.parse( window.localStorage.getItem('uid'));
                    //var name = JSON.parse( window.localStorage.getItem('name'));
                    window.location = "http://127.0.0.1:8888/dashboard";
                }
                else
                {
                    //alert("Wrong Username/Password!");
                    $scope.pass = "";
                    $scope.result = "Wrong Username/Password!";
                }
            })
        }

    });

//Dashboard Controller
    myApp.controller("dashboardController",function($scope,$http){




       $scope.username = JSON.parse( window.localStorage.getItem('username'));
       $scope.uid = JSON.parse( window.localStorage.getItem('uid'));
       $scope.name = JSON.parse( window.localStorage.getItem('name'));
       $scope.mySections = "Test Sections";
       $scope.sno = 1;


        $scope.logout = function(){
            //alert("Logout");
            window.localStorage.setItem('username','');
            window.localStorage.setItem('uid','');
            window.localStorage.setItem('name','');
            window.location = "http://127.0.0.1:8888";
        }

        $scope.loadMySections = function()
        {
            $http.get("http://127.0.0.1:8888/getMySections/"+$scope.uid).success(function(data){
                //alert(data);
                $scope.mySections = data;

             })
        }

        $scope.loadAllSections = function()
                {
                    //alert("Load All");
                    $http.get("http://127.0.0.1:8888/getSections").success(function(data){
                        //alert(data);
                        $scope.mySections = data;

                     })
                }

        //add section
        $scope.addSection = function(uid,sectionid){
            //alert(uid + " - "+ sectionid);
            $http.get("http://127.0.0.1:8888/enrollMySelf/"+uid+"/"+sectionid).success(function(data){

                //alert(data);
                if(data == '200')
                {
                    alert("Section Added Successfully");
                    window.location = "http://127.0.0.1:8888/dashboard";
                }
                else
                {
                    alert("Error Occurred, Please try again!");
                }

            })
        }

        //drop Section

        $scope.dropSection = function(uid,sectionid){
            //alert(uid + " - "+ sectionid);
            $http.get("http://127.0.0.1:8888/denrollMySelf/"+uid+"/"+sectionid).success(function(data){

                //alert(data);
                if(data == '200')
                {
                    alert("Section dropped Successfully");
                    window.location = "http://127.0.0.1:8888/dashboard";
                }
                else
                {
                    alert("Error Occurred, Please try again!");
                }

            })
        }

    });



