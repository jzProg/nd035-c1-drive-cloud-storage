# Super*Duper*Drive
My implementation of the final assessment project for Udacity's Java Web Developer Nanodegree Spring Boot Basics course.

## Description
<img src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>
A Cloud Storage based application, like Google Drive and Dropbox. Features:

- **Simple File Storage:** Upload/download/remove files
- **Note Management:** Add/update/remove text notes
- **Password Management:** Save, edit, and delete website credentials.

## Persistence
 In order to persist data on each application startup, i configured the H2 in-memory database to use file-based storage:
 `spring.datasource.url=jdbc:h2:file:/data/demo`

## Technologies

- The back-end with `Spring Boot`
- The front-end with `Thymeleaf`
- Application tests with `Selenium`
