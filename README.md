# CodeU Example Project

This is an example chat application. It's complete and functional, but leaves
plenty of room for improvement.

## Step 1: Download Java

Check whether you already have Java installed by opening a console and typing:

```
javac -version
```

If this prints out a version number, then you already have Java and can skip to
step 2. If the version number is less than `javac_1.8`, then you have an old
version of Java and should probably upgrade by following these instructions.

Download the JDK (not the JRE) from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html).

Retry the `javac -version` command **in a new console window** to check the
installation. If you still don't see a version number, then update your `PATH`
environment variable so it contains Java's `bin` directory. Follow [these
directions](https://www.java.com/en/download/help/path.xml) to do so.

## Step 2: Download Maven

This project uses [Maven](https://maven.apache.org/) to compile and run our
code. Maven also manages dependencies, runs the dev server, and deploys to App
Engine.

Download Maven from [here](https://maven.apache.org/download.cgi). Unzip the
folder wherever you want.

Make sure you have a `JAVA_HOME` environment variable that points to your Java
installation, and then add Maven's `bin` directory to your `PATH` environment
variable. Instructions for both can be found
[here](https://maven.apache.org/install.html).

Open a console window and execute `mvn -v` to confirm that Maven is correctly
installed.

## Step 3: Install Git

This project uses [Git](https://git-scm.com/) for source version control and
[GitHub](https://github.com/) to host our repository.

Download Git from [here](https://git-scm.com/downloads).

Make sure Git is on your `PATH` by executing this command:

```
git --version
```

If you don't see a version number, then make sure Git is on your `PATH`.

## Step 4: Setup your repository

Follow the instructions in the first project to get your repository setup.

## Step 5: Run a development server

In order to test changes locally, you'll want to run the server locally, on your
own computer.

To do this, open a console to your `codeu_project_2018` directory and execute this command:

```
mvn clean appengine:devserver
```

This tells Maven to clean (delete old compiled files) and then run a local
App Engine server.

You should now be able to use a local version of the chat app by opening your
browser to [http://localhost:8080](http://localhost:8080).

## Step 6: Make a change!

- Bring down the existing server by pressing `ctrl+c` in the console running the
App Engine devserver.
- Modify a `.java` or `.jsp` file. (Try updating the homepage by editing the
`index.jsp` file.)
- Bring the devserver back up by executing `mvn clean appengine:devserver`
again.
- Refresh your browser to see your changes!
