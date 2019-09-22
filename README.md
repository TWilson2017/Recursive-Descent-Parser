Write a recursive descent parser that will recognize function definitions like the following


function func1 ( a : integer , b : real, c : string )
[

     e is 1 ;

     g is 8 ;


]


All functions begin with the 'function' keyword.  That is followed by the function name.  
In this language there are only 3 function names: func1, func2, and func3.


After the function name there is a list of function parameters. 
There may be 0 or more.  Each parameter has a variable name, followed by a colon, 
followed by a data type. This language has only 7 variable names: a, b, c, d, e, f, g. 
It has only 3 data types: integer, real, string. The function signature is followed by opening bracket 
'[' to begin the function body. In the body of the function there may be 0 or more program 
assignment statements. Assignments are made with the 'is' keyword. Each statement ends with a semicolon. 
The closing bracket ends the function definition.



LEGAL STRINGS:

function func2 ( d : real , c : string )
[


]



function func1(  )
[

   
]

function func2 ( a : real , b : string )
[

    a is 4;


]

ILLEGAL STRINGS:

-------------------------------

function func3 (  b : string , int a)
[

   
]

function func3 ( c : integer)
[

   g = 4


]

function go ( c : integer ) 
[

   g is 4 ;


]