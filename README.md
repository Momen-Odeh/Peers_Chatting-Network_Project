# Peers Chatting


This project aims to develop a flexible chat application capable of employing both UDP (User Datagram Protocol) and TCP (Transmission Control Protocol) socket programming. The application facilitates real-time communication among users.


![Screenshot 2024-02-29 104156](https://github.com/Momen-Odeh/Network1/assets/92532348/4ef3a639-2854-4dea-8169-ab38cd0d18f2)



UDP for Peer-to-Peer Chatting

To achieve the essential chatting functionality, we'll employ UDP socket programming. UDP's suitability for peer-to-peer communication enables direct message exchange between users, ensuring low-latency and efficient communication among clients.

Peer-to-Peer Chat Communication

It's crucial to emphasize that while the TCP server plays a central role in managing active clients, the chat communication itself remains peer-to-peer. Clients will directly communicate with each other for their conversations, ensuring privacy and facilitating direct interaction between users.



![Screenshot 2024-02-29 104355](https://github.com/Momen-Odeh/Network1/assets/92532348/89e7db94-0473-4f63-8c5c-130f8fed0dde)





TCP for Managing Active Clients

To manage the list of active chat clients and facilitate communication, we used TCP socket programming. The TCP server will act as a central hub, keeping track of the clients currently engaged in chat sessions. It will also provide a list of active clients to other clients when requested.

Server's Role in Sharing Active Client List

The main task of the TCP server is to manage a constantly updating roster of active clients. Whenever a new client connects or an existing one disconnects, the server adjusts this list accordingly. Furthermore, it promptly responds to client inquiries by furnishing an updated list of active clients. This functionality closely resembles the behavior observed in well-known chat platforms such as Skype.

![Screenshot 2024-02-29 104515](https://github.com/Momen-Odeh/Network1/assets/92532348/4a13a944-c526-4442-b0d3-51a29dc558b7)



