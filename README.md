# COVID-19-Tracker

**How to use it?**

1. Download zip package from the repository
2. Unpack it on your computer
3. Use your favorite IDE to run Spring App
4. Open any browser and go to "localhost:xxxx/home", where "xxxx" is the free port on which application was run (most likely it will be on port 8080)

**What does this application do?**

This is a web application designed to track all new cases of Corona virus across the world as well as death toll and recoveries from COVID-19. Information about new cases changes every day for every country. Data based on which this application is build, is provided by Johns Hopkins Whiting School of Engineering GitHub profile (profile address below). 

I would like to point out that this application was build for recruitment purposes and should not be used as a reference in any media outlets. This project was build for educational and job-hunting purposes hence the source of specific information was not thoroughly checked. Information about specific cases in specific country may differ from other sources and as an author of this application I do not hold myself responsible for misinformation.

Source of data - https://github.com/CSSEGISandData/COVID-19

**Tools and Technologies used in this project:**

![toolsSpring1](https://user-images.githubusercontent.com/57737385/78133566-ee0ea700-741e-11ea-8741-7009ae9dc3a6.png)

**Biggest challenges I've encountered**

Biggest challenges involved implementing data from outside source with constant refreshing system. Data provided from another GitHub profile changes at certain points in time which meant that it was necessary to implement self-check inside the application. A mechanism that will update on itself every so often.

**What did I learn?**

Especially valuable was an experience of working with an actual outside source connected to the real-world problem. This application deals with sensitive matter of worldwide pandemic what makes this much more serious project than the other ones I've made so far.
