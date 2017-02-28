ReadME IMat:

**TODO:**

1. Layouts:

    * My account (new)
    * History
    * Product elements:
    
        * History
        * Checkout
        * Cart (Main view)
        
2. Design:

    * .CSS
    * Choose a color scheme
    * Specify our own categorization.
    
3. Backend:

    * Create basic navigation interaction
    * Make adapter for backend to include util-methods
    * Create category-handling for our own categories.
    
4. Fix problems from testing:

    * Create shopping lists without making a purchase.
    * You should be able to preview items shopping lists.
    * Make sure you can buy 1,5 KG of any lösviktsprodukt.
    * Dynamic next-button.
    * Ask before saving credit-card.
    * Structured input for credit-card.
    * Clicking twice on a Category causes an exception. 
    * How should nextButton behave on Search

**Notes**
    
    Should program launch in maximized mode?
    How should resizing be handled?
    Should resources be released when not used to minimize Memory usage?
    
**FXML Files:** 

   **cart.fxml:** 
   
       
   the UI for the cart(Varukorg).
   
   ![cart](http://gdurl.com/8udp)
        
        Notes for cart: A button for editing amount etc. 


        
   **checkout.fxml**
   
   the UI for the Checkout background (bakgrunden för utchekning)
   
   ![checkout](http://gdurl.com/NCA5)
   
        Notes for checkout: 
        is subject to change. 
        Button shape aswell as css theme are preliminary
        The Pane Size is subject to change. 
        The button Labels are to be done dynamically
        The Pane size is set to 800x480 px. 
        The button backgrounds should be transparent(CSS).
        Some Decicions regarding size must be handled. 
        
   **Confirmation.fmxl**
   
   The UI for the confirmation(Granska)
   
   ![confirmation](http://gdurl.com/iPnn)
   
        Notes for checkout: 
        
   **delivery.fmxl**
      
   The UI for the delivery(leverans)
    
   ![delivery](http://gdurl.com/LDlL)
    
        Notes for delivery: 
        
   **history.fxml**
          
   The UI for the purchase history(orderhistorik)
   
   ![history](http://gdurl.com/pwcG)
   
        Notes for history: 
   **main_window.fxml**
   
   the UI for the main Background window for all of the Shopping windows.
   
   ![main_window](http://gdurl.com/qEUp)
   
        notes for main background:
        
   **payment.fxml**
        
   the Payment section of the checkout.
   
   ![payment](http://gdurl.com/kXQe)
    
        notes for Payment: 
   **popup.fxml**
        
   The layout containing all of the popups, (Kryssrutan)
    
        notes for popup:
   **product_element.fxml**
   
   The layout for each of the products, that will later be added to each of the product grid.
   
        notes for product element: 
        
        Textfield for amount should be restricted to only numbers and should also mark all at mouseclick
   **product_grid.fxml**

   The the entire section containing product elements, the main label, sorting etc.
   
   ![grid](http://gdurl.com/EiE2)
        
        notes for product element:
   **receipt.fxml**
   
   The receipts for previous purchases. 
   
   ![receipt](http://gdurl.com/agC6)
   
        notes for the receipts.
   **shopping_lists.fxml**
        
   The saved shopping lists
   
   ![shopping_lists](http://gdurl.com/XrgX)
   
        notes for shopping lists
