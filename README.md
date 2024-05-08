# ***ENIGMA MACHINE***

* 3 Rotors R1-R2-R3
* 2 Directions D (fr - "droite") G (fr - "gauche")

Decide the rotor order, the direction when rotating of each, and the position at start (number of pre-rotations)

<img width="468" alt="Picture1" src="https://github.com/Loux14/Enigma/assets/122696881/850e3ea7-6547-4f95-a9f9-8950249432a7">

# Terminal command :

**java Enigma.java R1 D 0 R3 G 0 R2 D 7**

In this case you put R1 as first rotor, then R3 and finally R2.

R1 and R2 will turn to the right (D)
R3 will turn to the left (G)

R3 is already turned by 7 rotation at start.

Will be shown the initial setting and asked the message to encrypt.

Will be return the encrypted message and new position of rotors
