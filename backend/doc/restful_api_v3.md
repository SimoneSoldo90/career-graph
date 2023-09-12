# RESTFUL API
Seguendo le linee guida
https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design

### GET: Lettura di un oggetto
* La request non ha contenuti nel body
* La response contiene:
  * OK:
    * status HTTP-200 OK
    * nel body la rappresentazione json
  * ERRORE se non corrisponde alcun oggetto:
    * status HTTP-204 No content - in caso di array
    * status HTTP-404 Not found - per ricerca per id
    * inviare l'eventuale messaggio di errore nel body

### POST: Creazione di un oggetto
* La request contiene la rappresentazione json dell'oggetto
* La response contiene:
  * OK:
    * status HTTP-201 Created
    * nell'header location lo url dell'oggetto creato
    * nel body la rappresentazione json
  * ERRORE se dati non validi:
    * status HTTP-400 Bad request
    * inviare il messaggio di errore nel body

### PUT: Aggiornamento di un oggetto
* La request contiene la rappresentazione json dell'oggetto
* La response contiene:
  * OK:
    * status HTTP-200 OK
	* nel body la rappresentazione json
  * ERROR non è possibile aggiornare:
	* status HTTP-409 Conflict
	* inviare il messaggio di errore nel body

### DELETE: Eliminazione di un oggetto
* La request non ha contenuti nel body
* La response contiene:
  * OK:
	* status HTTP-204 No content
  * ERRORE non è possibile eliminare:
	* status HTTP-404 Not found - se l'oggetto non esiste
	* status HTTP-409 Conflict - se l'oggetto non è eliminabile
	* inviare il messaggio di errore nel body

## Elenco URL

### URL: /roadmaps/
* POST - crea una roadmap
	* request body: JSON roadmap
	* response body: JSON roadmap
* GET - ottiene tutte le roadmap
	* response body: JSON roadmap[]

### URL: /roadmaps/{roadmapId}
* GET - ottiene la specifica roadmap
	* response body: JSON roadmap
* PUT - aggiorna la specifica roadmap
	* request body: JSON roadmap
	* response body: JSON roadmap
* DELETE - rimuove la specifica roadmap

### URL: /steps/
* POST - crea uno step
	* request body: JSON step
	* response body: JSON step
* GET - ottiene tutti gli step
	* response body: JSON step[]

### URL: /steps/{stepId}
* GET - ottiene lo specifico step
	* response body: JSON step
* PUT - aggiorna lo specifico step
	* request body: JSON step
	* response body: JSON step
* DELETE - rimuove lo specifico step

### URL: /steps/{stepId}/roadmap-links/
* POST - crea un roadmap-link
	* request body: JSON roadmap-link
	* response body: JSON roadmap-link
* GET - ottiene tutti i roadmap-link dello step
	* response body: JSON roadmap-link[]

### URL: /steps/{stepId}/roadmap-links/{roadmapLinkId}
* GET - ottiene lo specifico roadmap-link
	* response body: JSON roadmap-link
* PUT - aggiorna lo specifico roadmap-link
	* request body: JSON roadmap-link
	* response body: JSON roadmap-link
* DELETE - rimuove lo specifico roadmap-link

### URL: /steps/{stepId}/resources
* POST - crea una risorsa per lo specifico step
	* request body: JSON resource
	* response body: JSON resource
* GET - ottiene tutte le risorse per uno step
	* response body: JSON resource[]

### URL: /steps/{stepId}/resources/{resourceId}
* GET - ottiene la specifica resource
	* response body: JSON resource
* PUT - aggiorna la specifica risorsa
	* request body: JSON resource
	* response body: JSON resource
* DELETE - rimuove la specifica resource

### URL: /skills/
* POST - crea una skill
	* request body: JSON skill
	* response body: JSON skill
* GET - ottiene tutte le skill
	* response body: JSON skill[]

### URL: /skills/{skillId}
* GET - ottiene la specifica skill
	* response body: JSON skill
* PUT - aggiorna la specifica skill
	* request body: JSON skill
	* response body: JSON skill
* DELETE - rimuove la specifica skill

### URL: /skills/{skillId}/resources
* POST - crea una risorsa per la specifica skill
	* request body: JSON resource
	* response body: JSON resource
* GET - ottiene tutte le risorse per una skill
	* response body: JSON resource[]

### URL: /skills/{skillId}/resources/{resourceId}
* GET - ottiene la specifica resource
	* response body: JSON resource
* PUT - aggiorna la specifica risorsa
	* request body: JSON resource
	* response body: JSON resource
* DELETE - rimuove la specifica resource

### URL: /users/{userId}
* GET - ottiene lo specifico utente
	* response body: JSON user

### URL: /users/{userId}/user-skills
* POST - crea un abbinamento user-skill
	* request body: JSON user-skill
	* response body: JSON user-skill
* GET - ottiene tutti i user-skill per un user
	* response body: JSON user-skill[]

### URL: /users/{userId}/user-skills/{userSkillId}
* GET - ottiene lo specifico abbinamento utente-skill
	* response body: JSON user-skill
* PUT - aggiorna lo specifico abbinamento utente-skill
	* request body: JSON user-skill
	* response body: JSON user-skill
* DELETE - rimuove lo specifico abbinamento utente-skill


## JSON objects

### JSON roadmap
```
{
    id: int, // non presente se POST request
    title: string,
    description: string,
	steps: [] // presente solo se GET response di /roadmaps/{roadmapId}
}
```

### JSON step
```
{
    id: int, // non presente se POST request
	roadmap_id: int,
	order: int,
    title: string,
    description: string,
	resources: [], // presente solo se GET response
	roadmap_links: [], // presente solo se GET response
	skills: [] // presente solo se GET response
}
```

### JSON roadmap_link
```
{
	step_id: int,
	roadmap_id: int,
    roadmap_title: string, // presente solo se GET response
    roadmap_description: string // presente solo se GET response
}
```

### JSON skill
```
{
    id: int, // non presente se POST request
    title: string,
    description: string,
	resources: [] // presente solo se GET response
}
```

### JSON resource
```
{
	step_id: int,
	skill_id: int,
	type: string,
	description: string,
	url: string
}
```

### JSON user
```
{
    id: int, // non presente se POST request
    sso_uid: string,
    first_name: string,
	last_name: string,
    email: string
}
```

### JSON user_skill
```
{
    id: int, // non presente se POST request
    user_id: int,
    skill_id: int,
	status: string
}
```