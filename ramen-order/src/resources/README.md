# Ramen Order App

## Product type
Ramen. A console application for ordering one bowl of ramen and customising it.

## Main features
- Choose a ramen: Cheese Ramen, Chicken Ramen or Carbonara Ramen. Prices are shown in the menu.
- Add toppings: egg, extra cheese, sausage, nori, mild sauce or spicy sauce.
- Mutually exclusive customisations: only one sauce is allowed per bowl, so mild and spicy sauce cannot be combined. The same topping cannot be added twice.
- Choose a size: Small (default), Medium (+$1.00) or Large (+$2.00).
- Undo the last change. Toppings and size changes can be undone one by one, in reverse order.
- Show the order with every item and its price, and the total.
- Notifications after choosing a ramen, after every customisation, after undo, and after placing or cancelling the order. Each one shows the updated description and price.
- Payment imitation by cash (asks for the amount and gives change) or by card (checks for a 16-digit number).
- Cancel the order (asks for confirmation first).

## Design patterns

### Creational: Factory (`RamenFactory`, `ToppingFactory`)
The menu only knows the number the user typed. The factories turn that number into the right object (`CheeseRamen`, `EggTopping`, ...), so `ConsoleMenu` never calls the concrete constructors. Each factory keeps one list of options, so adding a new ramen or topping means adding one line to that list. The menus are also printed from the same list, so they always match what can be created.

### Structural: Decorator (`RamenItem`, `ToppingDecorator`)
Every topping wraps a `RamenItem` and adds its own name and price to the item it wraps. Toppings can be combined in any order and any number without creating classes such as "ChickenRamenWithEggAndNori". The price and description of the bowl are worked out by passing the call down the chain. `Order` only works with the `RamenItem` interface, so it does not care whether the bowl is a plain ramen or has five toppings.

### Behavioural: Command (`Command`, `AddToppingCommand`, `SetSizeCommand`, `CommandHistory`)
Every customisation is a command object with `execute()` and `undo()`. `CommandHistory` keeps the commands that were applied in a list, so "Undo last change" just takes the last command and calls `undo()`. A command is only saved if it actually changed the order, so a blocked topping or the same size chosen twice cannot be undone by mistake.

### Additional behavioural pattern: Strategy (`PaymentMethod`, `CashPayment`, `CardPayment`)
`PaymentService` takes any `PaymentMethod` and calls `pay()`. Cash and card have different rules (change vs. card number check), but the service does not need to know which one it has. A new payment method can be added without changing `PaymentService`.

## Group members and Git usernames
| Git username | Student ID |
|---|---|
| Aida (GitHub account: adavoland) | 19984 |
| dialeelova (also shown as "Dia" in the first commit) | 20081 |

## Notes and assumptions
- Run `Main.main()` to start the application.
- Enter the number of an option and press Enter.
- One order is placed per run.
- Prices are in US dollars.
- Payment is only an imitation. No real money is taken and no card is charged. Any 16 digits are accepted as a card number. Spaces between digits are allowed.
- A cash amount can be written as `10`, `12.50` or `12,50`.
