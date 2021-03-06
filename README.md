This repository contains code, exercises and solutions from the book "Functional Programming in Java". Along with the book itself, it's the
closest you'll get to having your own private functional programming tutor without actually having one.

## How to use the project

The code is available as a Gradle project that may be used  by:

- Running Gradle commands in a terminal
- Importing in Eclipse
- Importing in IntelliJ

One may of course use only the source files to import them into any other editor and/or compile and run them from the command line. Running Gradle
commands will not be described here. If you choose this way, you should already know how to do it.

## Downloading the project

There are two ways to download this project. You may:

- Click on the **Download ZIP** button on this page (https://github.com/fpinjava/fpinjava) to download a ZIP archive, then unzip it in the directory
of your choice
- Or you can clone the project using Git, in order to be able to update it easily when modifications or additions are made to the project. For this,
you will need to have Git installed on your PC and use the URL available on this page in the **SSH clone URL** area.

## Importing into Eclipse

To import the project into Eclipse, you must first install the Gradle plug-in. To do this:

1. Select **Help > Eclipse** MarketPlace
1. In the **Find** text area, type **Gradle**
1. Click the **Go** button
1. Select the **Gradle integration for Eclipse** plugin and click the corresponding **Install** button.

You will have to accept installing unverified software, and then restarting Eclipse.

### Importing the project

You now need to import the `fpinjava-parent` project into Eclipse:

1. Select **File > Import > Gradle > Gradle Project**
1. In the **Import Gradle** project dialog box, click the **Browse** button, navigate to the directory were you put the project and select
the `fpinjava-parent` directory
1. Click on the **Build Model** button
1. In the dialog box, verify that all modules are selected. If they are not, selecting the parent module will automatically select all sub-modules.
1. Keep all standard settings untouched and click **Finish**. The project should now be imported.

## Importing into IntelliJ

To import the project into intelliJ:

1. Select **File > Import** project
1. In the **Select file or directory to import** dialog box, navigate to the directory where you have installed the project and select
the `fpinjava-parent` directory.
1. In the import project dialog box, select Gradle and click the **Next** button.
1. Click the **Finish** button
1. Find the `fpinjava-parent` project in the **Project** pane, right-click  the project and select **Open module settings**.
1. In the **Project Structure** dialog box, click on **Project** in the **Project setting** pane and select **8 - Lambdas, type annotations etc.**
for the **Project language level** option.
1. Click **OK**.

## Doing the exercises

For each chapter, you will find two modules called `chaptername-exercises` and `chaptername-solutions` . Go to the first exercise in
the `src/main/java` hierarchy. Here, you will find some code with either a comment saying "To be implemented" or method(s) with the
implementation replaced with a single line throwing a runtime exception. Just implement the missing code.

Note that code is often duplicated from one exercise to the another, so you should not look at the code for exercise 2 before doing exercise 1,
since exercise 2 will often contain the solution to exercise one.

## Verifying your answers

To verify that your solution is working, go to the corresponding unit test, in the `src/test/java` hierarchy of the same module. Right-click
on the test class (which has the same name as the exercise class with the addition of the `Test` suffix) and select **Run as JUnit test**. The test should
succeed. If it doesn't, fix your code and try again.

## Looking at solutions

If you don't find the correct solution to an exercise, you can look at the corresponding `chaptername-solutions` module. You may run the solution
test to verify that the solution is working.

## Remarks

Lots of code is duplicated. This is done so that all exercises are made as independent as possible. However, code reused from previous chapters
is copied to the `fpinjava-common` module and should be used from there.



