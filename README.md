# Files Organizer

Files Organizer is a Scala application designed to efficiently sort media files based on their Modification time.

## Dependencies
* Scala: Make sure Scala is installed on your system. If not, you can download it from Scala's official website.
* SBT: SBT (Scala Build Tool) is required to compile the application. You can install it by following the instructions on the SBT website.

## Installation steps

1. Clone the repository to your local machine:
    ```
    git clone https://github.com/V-Ader/FilesOrganizer.git
    ```
2. Navigate to the project directory:
    ```
    cd filesOrganizer
    ```
3. Compile the application using SBT:
    ```
    sbt compile
    ```

## Usage
Run the application with the following parameters:

**source directory**: The directory containing the files you want to sort. \
**target directory**: The directory where the sorted files will be moved. \
**error directory**: A directory where any files that encounter errors during sorting will be moved for further inspection. 

Example command:

```python
run /path/to/source/directory /path/to/target/directory /path/to/error/directory
```
The application will create subdirectories in the target directory based on the Modification time of the files. Each subdirectory will follow the YYYY-MM naming pattern.

Example:

```
/path/to/target/directory/2024-04/image.jpg
``` 
## License
This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/), which allows for open collaboration and use of the codebase. Feel free to improve it and contribute to its development!
