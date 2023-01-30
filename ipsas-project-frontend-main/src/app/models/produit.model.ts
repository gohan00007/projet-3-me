import { Categorie } from "./categorie.model";
import { Marque } from "./marque.model";
import { Taxe } from "./taxe.model";

export class Produit {

  public taxe: Taxe = new Taxe(1, 'timbre fiscale', 1, 'FIXE');
  public categorie: Categorie = new Categorie(1, 'PC');
  public marque: Marque = new Marque(0, 'MSI');

  constructor(
    public id: number,
    public nom: string,
    public designation: string,
    public quantite: number,
    public prixHT: number,
    public prixTVA: number,
    public prixTTC: number
  ) { }
}
