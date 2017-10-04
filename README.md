# Comp6651 programming assignment1 (java)
> About me: Concordia University    
>          Master of computer science  
>          email: yi93qin@gmial.com  
>          Linkin: [Linkin](https://www.linkedin.com/in/yi-qin-8188a5132/)  
>          Weibo: [Weibo](https://www.weibo.com/mygroups?gid=3544660121099343&wvr=6&leftnav=1&pids=plc_main)
___
## Description  
* Compare given sentence with a huge dictionary (contains 300,000 words) to find spelling mistakes. And give advice for correct, according to dictionary within a given *Levenshtein distance k*.
* Method: Simple linear search & Bk-tree
* To do list: (*ignore the simple linear search*)
1. calculate Levenshtein distance k (dynamic programming)
2. build BK-tree for dictionary
3. **Trick part:** if the distance between search word and root is bigger than given k, the subtree may be meet the requirements.
___
## Note:
* Run this code must have four files (vocab.txt, sentence.txt, MaxDistance.txt and output.txt).
* If the code have some problems, please contant me.

