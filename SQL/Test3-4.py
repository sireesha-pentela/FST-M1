def get_winner(player1, player2):
    if player1 == player2:
        return "It's a tie!"
    elif (player1 == "rock" and player2 == "scissors") or \
         (player1 == "scissors" and player2 == "paper") or \
         (player1 == "paper" and player2 == "rock"):
        return "Player 1 wins!"
    else:
        return "Player 2 wins!"

print("Rock, Paper, Scissors Game!")
print("Choices: rock, paper, scissors")

player1 = input("Player 1, enter your choice: ").lower()
player2 = input("Player 2, enter your choice: ").lower()

valid_choices = ["rock", "paper", "scissors"]

if player1 in valid_choices and player2 in valid_choices:
    result = get_winner(player1, player2)
    print(result)
else:
    print("Invalid input! Please choose rock, paper, or scissors.")