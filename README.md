Pyramid
=======

BrainTeaser for Amazon interview

Coding exercise:
 
Every athlete is characterized by his mass 'm' (in kg) and strength 's' (in kg). You are to find the maximum number of athletes that can form a tower standing one upon another. An athlete can hold a tower of athletes with total mass equal to his strength or less than his strength. Input contains the number of athletes n and their parameters. These inputs can be assumed to be passed as arguments (Integer n and List<Pair<Integer, Integer>> parameterList) appropriate for your language of choice:
For example:
 
n
m1 s1
m2 s2
...
mn  sn
If mi > mj then si > sj, but athletes with equal masses can be of different strength.
Number of athletes n < 100000. Masses and strengths are positive integers less than 2000000.
For example:
Input #1
 
4
3 4
2 2
7 6
4 5
Would yield
Output #1
 
3
 
