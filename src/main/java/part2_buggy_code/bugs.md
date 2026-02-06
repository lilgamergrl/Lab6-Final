# Expected Bugs Summary

## Bank Account (8 bugs):

1. Wrong comparison for deposit validation (< instead of <=)
2. Inverted status check for withdrawal (== instead of !=)
3. Wrong comparison for withdrawal limit (> instead of >=)
4. Missing break statement in switch (fall-through bug)
5. Modifying collection while iterating (ConcurrentModificationException)
6. Wrong balance check for overdrawn (== 0 instead of < 0)
7. Missing enum case handling in switch
8. Withdrawal count check should be >= not >