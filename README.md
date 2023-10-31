# zip_test
A simple compression algorithm to encrypt and decrypt data

## Functionning

The first byte is alocated to explain the length (n) of the compressed bytes

All the byte until "`0000 0000`" are alocated to defining a hashmap to convert numbers to characters

All the remaining bytes describes the data, it works by removing 8bits bytes to nbits bytes

## Example

Let's imagine you want to compress "aba", in bytes it becomes : [`0110 0001`,`0110 0010`,`0110 0001`]

Our algorithm will iterate over the bytes of the string and count the character occurences in a HashMap

For our example, it will be {a:2,b:1}

Knowing we only have 2 different characters, we can represent them in 2bits for example a -> `01` and b -> `10`

Now we have everything we need, knowing that the number of occurence is `0000 0010` in binary, 
the encoded bytes will be :
```plaintext
00000010 01100001 00000001 01100010 00000010 00000000 011001
^        ^        ^        ^        ^        ^        ^
the number of different characters  |        |        |
         |        |        |        |        |        |
         a in bits|        |        |        |        |
                  |        |        |        |        |
                  the conversion : a -> 01   |        |
                           |        |        |        |
                           b in bits|        |        |
                                    |        |        |
                                    the conversion : b -> 10
                                             |        |
                                             the delimiter
                                                      |
                                                      aba converted to 01 10 01
```

## Results 

Obviously this doesn't work well for small strings but for large data like Lorem Ipsum, you can expect between 25% of gain on byte size for normal text

It is to note that right now it doesn't support special characters like ç

## Expectations

Results can improve depending on the number of different characters, for the used Lorem Ipsum, you can fit all characters in 5 bits instead of 8 bits saving you about 37.5% for a more complicated text with more uppercase and punctuation you can save about 25%

## Limitations

The code doesn't support special characters like 'ç', may be added in the future

Right now it is efficient on small inputs, for big inputs, you should consider creating multiple encoding for example a file where you can save 37.5% and another where you can save 25% instead of saving 25% globally or worse 12.5%
                                                       
