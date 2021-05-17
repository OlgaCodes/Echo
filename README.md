# Echo #Client #Server #java

ENG
Simple console application that queues and notifies. Server receives a text message and time at which client wants to get message back. Server is able to handle multiple clients and multiple texts. Messages are queued by DelayQueue and the text message is stored in Item class that implements Delayed.

PL
Aplikacja klient-serwer służąca do kolejkowania i wysyłania notyfikacji. Serwer pobiera wiadomość tekstową od klienta oraz czas, o której ma odesłać wiadomość. Serwer może obsłużyć wiele klientów oraz wiele wiadomości w jednym czasie. Wiadomości są kolejkowane przy użyciu DelayQueue, a wiadomość przechowywana w klasie Item, która implementuje Delayed. 

Serwer:
![image](https://user-images.githubusercontent.com/84285452/118475643-c8b92700-b70c-11eb-854c-e8b78cc7a1ac.png)
Klient:
![image](https://user-images.githubusercontent.com/84285452/118475601-bf2fbf00-b70c-11eb-934f-d99cbe3fcd5b.png)
