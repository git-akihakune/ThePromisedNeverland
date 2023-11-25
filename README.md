# ThePromisedNeverland
This game is written as a project for the [O1](https://plus.cs.aalto.fi/o1/2023/w11/ch01/) programming course of Aalto University. Even so, I have tried my best to make it enjoyable. Hope you will have fun playing it as much as I had fun developing it.

Important information for TAs:
- This is not an IntelliJ module
- This is a git repository hosted on my Github: [The Promised Neverland](https://github.com/git-akihakune/ThePromisedNeverland)

# How to run
## For players
A binary release will be updated for each major version release. If you find no release in the [release tab](https://github.com/git-akihakune/ThePromisedNeverland/releases), please be patient. 

Otherwise, simply download the latest release and run it. The game was developed and tested for Linux terminal. Theoretically it should also run on MacOS. On Windows, please download [Windows Terminal](https://learn.microsoft.com/en-us/windows/terminal/install) and set it as default for the best experience.

## For developers
On theory, a JVM language like Scala should be platform independent. However, the source code was developed and tested only on Debian 11 and Ubuntu 20.04. If you use any other Linux distro or operating system, it is best to consider a VM to avoid errors. Docker containerization is still under development as you can find in the [Docker support branch](https://github.com/git-akihakune/ThePromisedNeverland/tree/dev/wip/docker-support). You can also report in [issues tab](https://github.com/git-akihakune/ThePromisedNeverland/issues) for problems regardless of the platform.

### 1. Clone the repository
```bash
git clone https://github.com/git-akihakune/ThePromisedNeverland.git
```

Or your can download the source as zip from Github, and unzip it wherever you like.

### 2. Install Scala
It goes without question that to build a Scala project, you will need Scala installed, specifically Scala compiler itself, and `sbt`. Simple instruction to install both can be found [in the official documentation](https://www.scala-lang.org/download/).

### 3. Move into the source directory
Important: It should be in `ThePromisedNeverland/thepromisedneverland`. The root directory is not the source directory. You will encounter errors if you try to run anything from the root directory.

```bash
cd ThePromisedNeverland/thepromisedneverland
```

### 4. Run the project
You should first compile the project to see if there is any compilation problem:
```bash
sbt compile
```

After which, if everything looks fine, you can run it with:
```bash
sbt run
```

Notice that if your machine/configuration does not have a sound device, for example, in a Docker container, you will crash into a runtime error. This edge case is acknowledged and will be fixed later.

# Documentation
You can find documentations under the `documentation` folder. Instructions for reading the documentation is in the `README.md` file in that directory.

The game map and walkthrough can also be found in the documentations.