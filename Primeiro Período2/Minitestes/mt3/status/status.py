mediaparcial = float(input())
if mediaparcial < 4.0 or mediaparcial > 7.0:
        print(f"média final: {mediaparcial:.1f}")
else:
    notafinal = float(input())
    mediafinal = (mediaparcial * 6) + (notafinal * 4) 
    print(f"média final: {mediafinal/10:.1f}")
