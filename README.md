# Simple-scripts-compiler
A compiler for simple automation scripts ( using java.awt.Robot class )

The project`s purpose is primarily to serve as a practice tool for the Computer Peripherals course at Technical University of Sofia.

The students have a predefined list of simple scenarios to automate via relatively short scripts. There is a very loose implementation
of a programming language("scripType") integrated in the application helping them do just that by providing a fascade of straightforward
commands. The basic concept is a command begins with a keyword and then parameters, separated by whitespaces.



Available commands in "scripType":
______________________________________________________________________________________________________________________________
- "line"
  
Description: Draws a straight line.There are two line types supported - continuous( flag = 1 ) and dashed ( flag = 0 )
  
Format: line [#color_hex_representation] [start_point_X] [start_point_Y] [end_point_X] [end_point_Y] [line_type]
  
Example: line #00FF00 250 300 400 300 1

------------------------------------------------------------------------------------------------------------------------------
- "point"
  
Description: Colors a pixel(effectively draws a point).

Format: point [#color_hex_representation] [point_X] [point_Y]
  
Example: point #FFFFFF 500 250

------------------------------------------------------------------------------------------------------------------------------
- "move"

Description: Moves the mouse cursor to a specified location.

Format: move [point_X] [point_Y]

Example: move 1000 600

------------------------------------------------------------------------------------------------------------------------------
- "click"
  
Description: Performs a click of a mouse button. Only generic button clicks supported, i.e. - left, right, double click left. 
  
Format: click [button]
  
Example: click leftdouble

------------------------------------------------------------------------------------------------------------------------------
- "press"
  
Description: Performs pressing of a special purpose button/combination(shortcut). Support is restricted to predefined use cases for now.
  
Format: press [special_button] || [special_button + any_button]
  
Example: press ALT+F4

------------------------------------------------------------------------------------------------------------------------------
- "delay"
  
Description: Delays next automation operation by given time in miliseconds. 
  
Format: delay [delay_time_ms]
  
Example: delay 2000

------------------------------------------------------------------------------------------------------------------------------
- "text"
  
Description: Performs typing of text from the keyboard. Of course in some cases support is limited by the locally installed keyboard. 
  
Format: text [text_plus_optional_special_symbols]
  
Example: text It`s something like this.

------------------------------------------------------------------------------------------------------------------------------
- "open"
  
Description: Opens up a program by executable(.exe) or opens up a website by address(leading with "www.") in user`s default browser.
  
Format: open [executable_with_extension_included] || [address_with_prefix_www]
  
Example: open notepad.exe  

______________________________________________________________________________________________________________________________






