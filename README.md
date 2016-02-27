# Simple-scripts-compiler
<h6>A compiler for simple automation scripts ( using java.awt.Robot class )</h6>

<b>The project`s purpose is primarily to serve as a practice tool for the Computer Peripherals course at Technical University of Sofia.</b>

<h5>The students have a predefined list of simple scenarios to automate via relatively short scripts. There is a very loose implementation
of a programming language("scripType") integrated in the application helping them do just that by providing a fascade of straightforward
commands. The basic concept is a command begins with a keyword and then parameters, separated by whitespaces.</h5>


<h3>DEMO:</h3>

<a href="https://gyazo.com/ffc48d8affb311f03a3889a23fd24905"><img src="https://i.gyazo.com/ffc48d8affb311f03a3889a23fd24905.gif" alt="https://gyazo.com/ffc48d8affb311f03a3889a23fd24905"/></a>

<p>Available commands in "scripType":</p>


______________________________________________________________________________________________________________________________
- <b>line</b>
  
Description: Draws a straight line.There are two line types supported - continuous( flag = 1 ) and dashed ( flag = 0 )
  
Format: <i>line [#color_hex_representation] [start_point_X] [start_point_Y] [end_point_X] [end_point_Y] [line_type]</i>
  
Example: <i>line #00FF00 250 300 400 300 1</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>point</b>
  
Description: Colors a pixel(effectively draws a point).

Format: <i>point [#color_hex_representation] [point_X] [point_Y]</i>
  
Example: <i>point #FFFFFF 500 250</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>move</b>

Description: Moves the mouse cursor to a specified location.

Format: <i>move [point_X] [point_Y]</i>

Example: <i>move 1000 600</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>click</b>
  
Description: Performs a click of a mouse button. Only generic button clicks supported, i.e. - left, right, double click left. 
  
Format: <i>click [button]</i>
  
Example: <i>click leftdouble</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>press</b>
  
Description: Performs pressing of a special purpose button/combination(shortcut). Support is restricted to predefined use cases for now.
  
Format: <i>press [special_button] || [special_button + any_button]</i>
  
Example: <i>press ALT+F4</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>delay</b>
  
Description: Delays next automation operation by given time in miliseconds. 
  
Format: <i>delay [delay_time_ms]</i>
  
Example: <i>delay 2000</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>text</b>
  
Description: Performs typing of text from the keyboard. Of course in some cases support is limited by the locally installed keyboard. 
  
Format: <i>text [text_plus_optional_special_symbols]</i>
  
Example: <i>text It`s something like this.</i>

------------------------------------------------------------------------------------------------------------------------------
- <b>open</b>
  
Description: Opens up a program by executable(.exe) or opens up a website by address(leading with www prefix) in the default browser.
  
Format: <i>open [executable_with_extension_included] || [address_with_prefix_www]</i>
  
Example: <i>open notepad.exe</i>

______________________________________________________________________________________________________________________________
