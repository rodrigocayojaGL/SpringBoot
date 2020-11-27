# Common Logger Live Example(Aspect Based)

This proyect is an example of utilization of the Common Logger Library, implmenting a basic microservice on the port 8080:

| path | verb | description | example | 
| -- | -- | -- | -- |
| `/item` | `GET` | Fetchs all the data from the in-memory db | `[{"id": 1,"name": "nails","group": "top-shelf"}]` | 
| `/item` | `POST` | Adds an item to the in-memory db with a 10% chance of failure | `{"name":"nails","group":"top-shelf"}` | 
