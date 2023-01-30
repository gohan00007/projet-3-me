import { Produit } from "./produit.model";

export class LigneFacture {
  public produit: Produit = new Produit(0, '', '', 0, 0, 0, 0);
  public quantite: number = 0;
  constructor(
    public id: number,
    public produitId: number,
    public prixHT: number,
    public prixTVA: number,
    public prixTTC: number
  ) { }
}
