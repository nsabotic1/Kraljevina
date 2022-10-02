# Kraljevina
Program koji dodaje kraljevski grad u kraljevsku državu.
Grad može biti kraljevski grad samo ako se nalazi u kraljevskoj državi. Ako se grad proba
proglasiti za kraljevski, a nije u kraljevskoj državi, treba izbaciti dijalog (Alert tipa Error) sa
porukom "Država xxx nije kraljevska država". Također, metoda setKraljevski klase Grad u tom
slučaju treba baciti izuzetak tipa NotAKingdomException sa istim tekstom.
