def inverte3a3(s):
    string_nova = ""
    for i in range(len(s) -3 , -1 , -3):
        for j in range(3):
           string_nova += s[i+j] 
    return string_nova

assert inverte3a3("paisimtio") == "tiosimpai"
