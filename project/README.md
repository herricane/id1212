## ID1212 Project API:s

Backend RESTful API:s using Spring Boot.

Base URL: `http://localhost:xxxx/api`

| METHOD | ENDPOINT                  | USAGE                         | RETURNS     |
| ------ | ------------------------- | ----------------------------- | ----------- |
| POST   | /register                 | Register a new user           | -           |
| POST   | /login                    | Log in                        | -           |
| GET    | /users/{user_id}          | Get a user                    | user        |
| GET    | /users/{user_id}/username | Get a user's username         | username    |
| GET    | /user/{user_id}/issues    | Get a list of a user's issues | issues      |
| POST   | /users/{user_id}/issues   | Add an issue to a user        | -           |
| GET    | /issues                   | Get all issues                | issues      |
| GET    | /issues/{id}              | Get an issue                  | issue       |
| GET    | /issues/{id}/title        | Get an issue's title          | title       |
| GET    | /issues/{id}/description  | Get an issue's description    | description |
| GET    | /issues/{id}/status       | Get an issue's status         | status      |
| GET    | /issues/{id}/timestamp    | Get an issue's timestamp      | timestamp   |
| PUT    | /issues/{id}              | Modify an issue               | -           |
| DELETE | /issues/{id}              | Delete an issue               | -           |

