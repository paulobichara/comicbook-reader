define({ "api": [
  {
    "type": "get",
    "url": "/comicBooks/",
    "title": "Get all Comic Books",
    "name": "GetAllComicBooks",
    "group": "ComicBook",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "recordsTotal",
            "description": "<p>Number of all Comic Books</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "recordsFiltered",
            "description": "<p>Number of filtered Comic Books</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Object[]</p> ",
            "optional": false,
            "field": "data",
            "description": "<p>List of comic book objects</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "ComicBooksServiceException",
            "description": "<p>Some error occurred - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "comicbook-reader-service/src/main/java/org/paulobichara/comics/service/ComicBooksServices.java",
    "groupTitle": "ComicBook"
  },
  {
    "type": "get",
    "url": "/comicBooks/:comicBookId",
    "title": "Get a specific comic book",
    "name": "GetComicBook",
    "group": "ComicBook",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "comicbookId",
            "description": "<p>Comic Books unique ID</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>comic book unique id</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "title",
            "description": "<p>comic book title</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "description",
            "description": "<p>comic book description</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "sequenceNumber",
            "description": "<p>comic book sequenceNumber</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "type",
            "description": "<p>comic book type</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "rating",
            "description": "<p>comic book rating</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "ComicBooksServiceException",
            "description": "<p>Some error occurred - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "comicbook-reader-service/src/main/java/org/paulobichara/comics/service/ComicBooksServices.java",
    "groupTitle": "ComicBook"
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p> "
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "comicbook-reader-service/target/comicbook-reader-service-1.0.0-SNAPSHOT/docs/main.js",
    "group": "_home_monk_Develop_workspace_sample_javaee_comicbook_reader_comicbook_reader_service_target_comicbook_reader_service_1_0_0_SNAPSHOT_docs_main_js",
    "groupTitle": "_home_monk_Develop_workspace_sample_javaee_comicbook_reader_comicbook_reader_service_target_comicbook_reader_service_1_0_0_SNAPSHOT_docs_main_js",
    "name": ""
  }
] });