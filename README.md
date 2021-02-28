<h1 align="center">
<img
		width="250"
		alt="Collab"
		src="https://s3.amazonaws.com/media-p.slid.es/uploads/1265873/images/7486630/Group_3.png">
</h1>
<h1 align="center">
	Collab - Find a peer tinder-style
</h1>

<p align="center">
	<img alt="Last Commit" src="https://img.shields.io/github/last-commit/ankit039/HackVerse_2.0-No_Goals-.svg?style=flat-square">
	<img alt="Licence" src="https://img.shields.io/github/license/ankit039/HackVerse_2.0-No_Goals-.svg?style=flat-square">
	<img alt="Star" src="https://img.shields.io/badge/you%20like%20%3F-STAR%20ME-blue.svg?style=flat-square">
</p>

<p align="center">
	<img src="https://im2.ezgif.com/tmp/ezgif-2-afc0f1958e92.gif" width="300">
</p>


## Overview

**Perfect to start an Collab.** According to various research, we tend to learn better when we have someone to learn with. But, at the same time, there is no good way to find our study partner which matches our skills and knowledge.

So, we thought of creating an android application which can help the users in finding our next study partner. Our application matches 2 different users on the basis of their interests and skills and that too tinder-style. The user swipes right on other users which they think will be able to help them in studying and then, if the same other users swipes them right too, then both users matches and both of them can chat with each other and study together.

We only want to match 2 users and help them find each other. We are using android as our front-end and developing our own custom back-end with NodeJS and SQLite..


## Screenshots

<img
		width="210"
		alt="Capture 1"
		src="https://cdn.discordapp.com/attachments/815107134076878868/815448635797143562/Screenshot_2021-02-28-10-29-35-503_com.example.collabfrontend.jpg">
<img
		width="210"
		alt="Capture 2"
		src="https://cdn.discordapp.com/attachments/413814389301313561/815449572184293446/1614488722280.jpg">
<img
		width="210"
		alt="Capture 3"
		src="https://cdn.discordapp.com/attachments/815107134076878868/815448636723822623/Screenshot_2021-02-28-09-44-10-489_com.example.collabfrontend.jpg">
<img
		width="210"
		alt="Capture 4"
		src="https://cdn.discordapp.com/attachments/815107134076878868/815448637186375710/Screenshot_2021-02-28-10-30-24-193_com.example.collabfrontend.jpg">



## Props

### CardItem

| Name           | Type     | Required | Description                                               | Example                                             |
| -------------- | -------- | -------- | --------------------------------------------------------- | --------------------------------------------------- |
| `image`        | string   | Yes      | Picture of member.                                        | `image="<base64 string>"`                           |
| `Name`         | string   | Yes      | Name of member.                                           | `name="Ankit R"`                                    |
| `skills`       | string   | Yes      | Skills of member.                                         | `skills=["react","node"]`                           |
| `matches`      | string   | Yes      | Match percentage.                                         | `matches="95"`                                      |
| `onSwipeLeft`  | function | No       | Swipe card to left.                                       | `add other user to rejected list`                   |
| `onSwipeRight` | function | No       | Swipe card to right.                                      | `add other user to connected list`                  |

### Message

| Name          | Type   | Required | Description             | Example                                                                                      |
| ------------- | ------ | -------- | ----------------------- | -------------------------------------------------------------------------------------------- |
| `image`       | string | Yes      | Picture of member.      | `image="<base64 string>"`                                                                    |
| `userName`    | string | Yes      | userName of member.     | `name="ankit039"`                                                                            |
| `lastMessage` | string | Yes      | Last message of member. | `lastMessage="Wanna study about ReactContext Togther?"` |


### ProfileItem


| Name           | Type     | Required | Description                                               | Example                                             |
| -------------- | -------- | -------- | --------------------------------------------------------- | --------------------------------------------------- |
| `image`        | string   | Yes      | Picture of member.                                        | `image="<base64 string>"`                           |
| `Name`         | string   | Yes      | Name of member.                                           | `name="Ankit R"`                                    |
| `skills`       | string   | Yes      | Skills of member.                                         | `skills=["react","node"]`                           |
| `matches`      | string   | Yes      | Match percentage.                                         | `matches="95"`                                      |
| `Connect`      | card     | No       | Card of connected user.                                   | `card of connedted user`                            |
| `Accept`       | card     | No       | Card of accpeted user.                                    | `card of accepted user`                             |
| `Reject`       | card     | No       | Card of rejected user.                                    | `card of rejected user`                             |


## Star, Fork, Clone & Contribute

Feel free to contribute on this repository. If my work helps you, please give me back with a star. This means a lot to me and keeps me going!

## Contributors

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore -->
<table>
  <tr>
<td align="center"><a href="https://github.com/lucifer0987"><img src="https://avatars.githubusercontent.com/u/34437499?s=460&v=4" width="100px;" alt="Gaurav Kumar"/><br /><sub><b>Gaurav Kumar </b></sub></a><br /><a href="https://github.com/stevenpersia/HackVerse_2.0-No_Goals-/commits?author=lucifer0987" title="Frontend">💻</a></td>
<td align="center"><a href="https://github.com/ankit039"><img src="https://avatars.githubusercontent.com/u/44525721?s=460&v=4" width="100px;" alt="Ankit Raj"/><br /><sub><b>Ankit Raj </b></sub></a><br /><a href="https://github.com/stevenpersia/HackVerse_2.0-No_Goals-/commits?author=ankit039" title="Backend">💻</a></td>
 <td align="center"><a href="https://github.com/aman5683"><img src="https://avatars.githubusercontent.com/u/50030658?s=460&v=4" width="100px;" alt="Aman Ghatiya"/><br /><sub><b>Aman Ghatiya </b></sub></a><br /><a href="https://github.com/stevenpersia/HackVerse_2.0-No_Goals-/commits?author=aman5683" title="Backend">💻</a></td>
  </tr>
</table>

<!-- ALL-CONTRIBUTORS-LIST:END -->
