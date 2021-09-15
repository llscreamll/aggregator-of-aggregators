    API-REST
=========================================================
    POST /register

    Parameter:
    login     type string
    password  type string

    Example:  
    { 
    "login" : "test",
    "password" : "test"
    }
    
    response :
    id     type int,
    token  type string
==========================================================
    POST /auth/login

    Parameter:
    login     type string
    password  type string

    Example:  
    { 
    "login" : "test",
    "password" : "test"
    }
    
    response :
    id     type int,
    token  type string
==========================================================
    GET /prices
    
    Header:
    Authorization	string	Authorization token "Bearer [auth-token]"

    Parameter:
    start_place_address     type string
    start_place_address     type string

    Example:  
    
    http://localhost:8080/prices?start_place_address=Moskow&finish_place_address=Balashikha
    
    response :
    expensive   type array
    regular     type array
    cheap       type array
==========================================================
    GET /prices/{brand}
    
    Header:
    Authorization	string	Authorization token "Bearer [auth-token]"

    Parameter:
    brand                   type string (expensive | regular | cheap)
    start_place_address     type string
    start_place_address     type string

    Example:  
    
    http://localhost:8080/prices/expensive?start_place_address=Moskow&finish_place_address=Balashikha
    
    response :
    expensive   type array
==========================================================
    POST /order
    
    Header:
    Authorization	string	Authorization token "Bearer [auth-token]"

    Parameter:
    brand          type string (expensive | regular | cheap)
    carId          type integer
    startAddress   type string
    endAddress     type string

    Example:  
  
  {
    "brand" : "expensive",
    "carId" : 1,
    "startAddress" : "Moskow",
    "endAddress" :   "Balashikha"
}
    
    response :
    id            type integer,
    info          type string;
    price         type float;
    modelCar      type string;
    brandName     type string;
    startAddress  type string;
    endAddress    type string;
    dateCreated   type localDateTime;
    services      type string[];
==========================================================
    POST /order/cancel/{orderId}
    
    Header:
    Authorization	string	Authorization token "Bearer [auth-token]"

    Parameter:
    orderId  type integer

    Example:  
  
    http://localhost:8080/order/cancel/1
    
    response :
    info  type string
==========================================================

