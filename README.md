# Telephone_Network
Java: Minimum Spanning Tree
> This Java project uses Dijkstra's algorithm to identify the maximum bandwidth that information can be transmitted between two switching centers of a telephone network.


## Table of Contents
* [Technologies Used](#technologies-used)
* [Setup](#setup)
* [Project Status](#project-status)
* [Detailed Information](#detailed-information)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)
* [License](#license)


## Technologies Used
- Eclipse IDE - version 4.24.0


## Setup
The project is designed to run in the console and take in information about the various switching centers and bandwidth of the communication lines from a file named input.txt. 
Required format for input.txt:
- Line 1: id of each switching center separated by a semicolon
- Line 2: id of starting center
- Line 3: id of ending center
- Line 4 - ?: id of the two centers making up a communication line and the bandwidth between them separated by a semicolon 

 File with alternate examples of input.txt included in repo.


## Detailed Information
The telephone network uses a graph to store the switching centers (vertices in the graph) in the network and the communication lines (edges in the graph) between those centers. Each communication line has a specific maximum bandwidth at which the information can be transmitted. This program determines the maximum bandwidth that information can be transmitted between two centers by determining the minimum bandwidth on the highest potential bandwidth path between two switching centers.  
The Graph class is where the bulk of the work takes place. It contains the addEdge() and addVertex() methods which are used to take in the input of the edges and vertices and place them with the graph so that the bandwidth calculation can take place. Additionally Graph contains the findMaximumBandwidthPath() method which is responsible for finding and displaying the correct path. 
This Java method takes in two strings, start and end, representing the start and end vertices of a path in a graph. It first checks if the start and end vertices exist in the graph. If either of them does not exist, it prints an error message and returns. If both vertices exist but are the same, it also prints an error message and returns.
If the start and end vertices are different and both exist in the graph, the method uses Dijkstra's algorithm to find the shortest path between the two vertices in terms of maximum bandwidth (the minimum bandwidth among all edges in the path). It does this by maintaining a priority queue of vertices sorted by their bandwidth, and repeatedly extracting the vertex with the highest bandwidth from the queue. For each extracted vertex, it updates the bandwidth of its neighboring vertices by taking the minimum between their current bandwidth and the bandwidth of the edge connecting them to the extracted vertex.
If the end vertex is not reached (i.e. it is not present in the queue when the queue becomes empty), it means that there is no path between the start and end vertices, and an error message is printed. Otherwise, the method traverses the path from the end vertex to the start vertex, storing the character values of the vertices in a list. It then reverses the list and prints the maximum bandwidth path. If the bandwidth of the end vertex is less than or equal to 0, it prints an error message.
The running time of the method is O(E log V) where E is the number of edges in the graph and V is the number of vertices in the graph. This is because the method uses Dijkstra's algorithm to find the shortest path between the start and end vertices, which has a running time of O(E log V). Specifically, the running time is dominated by the while loop that executes as long as the priority queue (implemented as a heap) is not empty, which has a running time of O(E log V) because each edge is inserted into and removed from the priority queue once. The remaining operations in the method, such as checking if the start and end vertices exist in the graph, adding vertices to the path list, and reversing the path list, have a running time of O(V) or O(E) and do not significantly affect the overall running time of the method.


## Project Status
Project is: _complete_ 


## Acknowledgements
- This project was completed as part of my Algorithms course.


## Contact
Created by [@alexjmoore8](https://www.linkedin.com/in/alexmoore8/) - feel free to contact me!


## License 
MIT License

Copyright (c) 2023 Alex Moore

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.




