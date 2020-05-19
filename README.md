# Caleb-Hoff-GUI-Toolkit

This toolkit aims to simplify the process of making stylish and modern, cross-platform GUIs.

1) JFrameCH
Looking to make a simple java app? Everyone starts by creating a JFrame. The issue, however, is that these look incredibly dated and ugly without a lot of work. JFrameCH makes things a lot simpler in a few ways. First, just by adding the letters "CH" to the instantiation of a JFrame, you can create a much nicer looking frame very quickly.

Comparsion JFrame vs JFrameCH:
<img src='https://i.postimg.cc/1tzvZvQJ/comparison.png' alt='comparison'/>

It's very simple to use. Just import the Caleb Hoff library and instantiate a JFrame using JFrameCH(). This is the code for the example seen above.

How to use:
<img src='https://i.postimg.cc/7YSd9QY2/code.png' alt='code'/>

Final Notes:
1) Due to how JFrameCH works, it is not recommended that you use the JFrame.setLayout() function. Instead, to control the layout it is recommended that you create a panel inside a JFrameCH and set its layout. This will prevent graphical issues from occuring with the title bar and window wrapper.
