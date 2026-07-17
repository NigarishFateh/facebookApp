# Facebook Graph App

This document explains **what we built**, **how it works**, **why it is called a graph**, and **what Spring Boot has to do with it**.

---

## 1. What is this project?

This is a **mini Facebook** that runs in the terminal (console).

You can:

1. Create users  
2. Create posts  
3. Like posts  
4. Comment on posts  
5. Display users and posts  

It stores everything in **memory** (inside Java objects / lists).  
When you close the program, the data is gone (no database is used yet for this graph app).

---

## 2. Project folders (structure)

```
src/main/java
├── model/          ← the "things" (data)
│   ├── User.java
│   ├── Post.java
│   ├── Comment.java
│   └── Like.java
├── service/        ← the "manager" (business logic)
│   └── FacebookService.java
└── app/            ← the "start button" (menu)
    └── Main.java
```

### Easy meaning of each package

| Package   | Role in plain English                                      |
|-----------|------------------------------------------------------------|
| `model`   | Blueprints of Facebook things: User, Post, Like, Comment   |
| `service` | The brain that creates and connects those things           |
| `app`     | Shows the menu and takes your keyboard input               |

---

## 3. What each class does

### User.java
A person on Facebook.

- `id` — unique number  
- `name` — e.g. Ali  
- `email` — e.g. ali@email.com  
- `posts` — a **list** of that user’s posts  

**One user can have many posts.**

---

### Post.java
A post written by a user.

- `id`  
- `caption` — the text of the post  
- `author` — which **User** wrote it  
- `likes` — list of likes  
- `comments` — list of comments  

**One post can have many likes and many comments.**

---

### Like.java
A like on a post.

- `id`  
- `likedBy` — which **User** liked it  

---

### Comment.java
A comment on a post.

- `id`  
- `text` — comment message  
- `commentedBy` — which **User** wrote the comment  

---

### FacebookService.java
This is like **Facebook the company**.

It keeps:

- all users  
- all posts  

And does actions:

- create user  
- create post  
- like post  
- comment on post  
- display users / posts  

It also creates IDs automatically (`1, 2, 3...`).

---

### Main.java
This is where the program **starts**.

It shows this menu:

```
========= FACEBOOK =========
1. Create User
2. Create Post
3. Like Post
4. Comment
5. Display All Users
6. Display Posts
7. Exit
```

It uses `Scanner` to read what you type, then calls `FacebookService`.

---

## 4. How the whole app works (step by step)

Think of it like a restaurant:

1. **Main** = waiter taking your order (menu + Scanner)  
2. **FacebookService** = kitchen that prepares everything  
3. **model classes** = the food / data  

### Example flow

```
You choose 1 → Create User "Ali"
        ↓
Main reads name + email
        ↓
FacebookService.createUser(...)
        ↓
New User object is created
        ↓
User is stored in FacebookService's users list
```

```
You choose 2 → Ali creates post "Hello Java"
        ↓
Service finds User by ID
        ↓
Creates Post object
        ↓
Adds Post to User.posts
        ↓
Also adds Post to Service.posts list
```

```
Sara likes Ali's post
        ↓
Service finds Post + User (Sara)
        ↓
Creates Like object (likedBy = Sara)
        ↓
Adds Like into Post.likes list
```

```
Ahmed comments "Nice Post!"
        ↓
Creates Comment object (commentedBy = Ahmed)
        ↓
Adds Comment into Post.comments list
```

Everything becomes connected through object references (pointers to other objects).

---

## 5. Why this is a GRAPH structure

A **graph** means:

- **Nodes** = objects (User, Post, Like, Comment)  
- **Edges / links** = connections between them  

In our app, we do **not** store data as one flat table only.  
We store objects that **point to other objects**.

### Picture of the graph

```
        [User: Ali]
             |
             | owns
             ▼
      [Post: Hello Java]
         /            \
        / likes        \ comments
       ▼                ▼
 [Like by Sara]   [Comment by Ahmed]
       |                 |
       ▼                 ▼
  [User: Sara]     [User: Ahmed]
```

### Nodes (circles)

| Node type | Example            |
|-----------|--------------------|
| User      | Ali, Sara, Ahmed   |
| Post      | "Hello Java"       |
| Like      | Sara’s like        |
| Comment   | Ahmed’s comment    |

### Edges (arrows / links)

| From     | To       | Meaning                    |
|----------|----------|----------------------------|
| User     | Post     | User has many posts        |
| Post     | User     | Post knows its author      |
| Post     | Like     | Post has many likes        |
| Like     | User     | Like knows who liked       |
| Post     | Comment  | Post has many comments     |
| Comment  | User     | Comment knows who wrote it |

That network of objects = **graph structure**.

---

## 6. How everything is linked as nodes (in code)

### Link 1: User → Posts

Inside `User`:

```java
private List<Post> posts;
```

So from one User node, you can walk to many Post nodes.

### Link 2: Post → Author (User)

Inside `Post`:

```java
private User author;
```

So a Post node points back to its User node.

### Link 3: Post → Likes

```java
private List<Like> likes;
```

### Link 4: Like → User

```java
private User likedBy;
```

### Link 5: Post → Comments

```java
private List<Comment> comments;
```

### Link 6: Comment → User

```java
private User commentedBy;
```

### Mental model

```
User ──► Post ──► Like ──► User
              └──► Comment ──► User
```

You can “travel” from one object to related objects, just like following friends on a social network graph.

---

## 7. Real example (Phase 11 test)

Actions:

1. Create users: **Ali**, **Sara**, **Ahmed**  
2. Ali posts: **Hello Java**  
3. Sara likes it  
4. Ahmed comments: **Nice Post!**  

Result when you display posts:

```
User: Ali
Posts
--------------------------------
Hello Java

Likes:
Sara

Comments:

Ahmed
Nice Post!
```

Why this proves the graph works:

- Post knows author = Ali  
- Post knows likes = Sara  
- Post knows comments = Ahmed + text  

All linked objects were found by walking the graph.

---

## 8. How Spring Boot fits in this project (important)

### Honest and simple answer

**Your Facebook Graph App (menu + Users/Posts) is currently plain Java.**  
It does **not** use Spring Boot features yet.

### What exists in the project

Because this folder was created as a **Spring Boot Maven project**, you also have:

| File / thing | What it is |
|--------------|------------|
| `pom.xml` | Maven file with Spring Boot dependencies |
| `FacebookAppApplication.java` | Default Spring Boot starter class |
| `application.properties` | Spring Boot config file |

### What your graph app actually runs

You run:

```text
app.Main
```

That class:

- creates `FacebookService` with `new FacebookService()`
- uses `Scanner` for input
- stores data in `ArrayList`s in memory

No Spring annotations like `@Service`, `@RestController`, `@Autowired` are used in the graph code.

### So what is Spring Boot doing right now?

**Almost nothing for this graph menu app.**

Spring Boot is like a powerful engine sitting in the garage:

- The car (Spring Boot project) is ready  
- But you are currently riding a bicycle (`Main` + `FacebookService`)  

### Two different “start buttons”

| Class | What happens if you run it |
|-------|----------------------------|
| `app.Main` | Console Facebook menu (graph app) — **this is what we built** |
| `FacebookAppApplication` | Starts Spring Boot web/server style app (default Spring project) |

### When Spring Boot *would* be used later

You could upgrade this project to Spring Boot by:

1. Making REST APIs (`/users`, `/posts`)  
2. Saving Users/Posts in a database with JPA  
3. Using `@Service`, `@Entity`, `@RestController`  

Then Spring Boot would manage objects, HTTP requests, and database connections.

Right now, that next step is **not implemented**. This phase is about learning the **graph of objects** first.

---

## 9. One-line summary of each layer

```
Main (menu)
   ↓ calls
FacebookService (manager)
   ↓ creates / connects
User / Post / Like / Comment (graph nodes)
```

---

## 10. Quick FAQ

### Is data saved forever?
No. It lives only while the program is running (in memory).

### Is this real Facebook?
No. It is a learning model of Facebook relationships.

### Why lists (`List<>`)?
Because one node can connect to **many** other nodes  
(one user → many posts, one post → many likes/comments).

### Why is it called a graph?
Because objects are nodes, and references between objects are edges.

### Is Spring Boot required to understand this?
No. First understand User–Post–Like–Comment links. Spring Boot can come later.

---

## 11. Final takeaway

You built a **social network graph in Java**:

- **Nodes** = User, Post, Like, Comment  
- **Edges** = who owns what, who liked, who commented  
- **Manager** = FacebookService  
- **UI** = console menu in Main  

Spring Boot is present in the project setup, but **the graph application itself currently runs as plain Java**.

Once this graph is clear in your mind, you are ready to move it into Spring Boot APIs + database later.
