# Document classification website for sustainable development goals

## Purpose
The aim of the project is to access a model on the web that can predict which sustainable development goal an article can be associated with.
For more info about sustainable development goals,  [visit]([https://www.google.com](https://sdgs.un.org/goals)https://sdgs.un.org/goals)

## Example Usage

By entering the title, abstract and author's keywords of the article, you can get an estimate of which sustainable development goal it is associated with.

![Ekran Resmi 2024-02-27 23 12 36](https://github.com/ecagataydogan/classification-web/assets/101594855/ee2d42c3-aa09-4dfa-a206-6fe872e0f00e)





## Used Technologies
### Backend
- Spring Boot

- FastAPI
### Frontend
- React + Vite
### Model (more info checkout my [repo]([https://www.google.com](https://sdgs.un.org/goals)https://sdgs.un.org/goals))
- Python
- Transformers
- Pytorch

## How to run

### Frontend

`npm install`

`npm run dev`

### Backend Spring 

`mvn package`

`java -jar target/document-classification-backend-0.0.1-SNAPSHOT.jar`

### Backend FastAPI

`uvicorn app.main:app --reload`
