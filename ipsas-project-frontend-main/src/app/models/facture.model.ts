import { Client } from "./client.model";
import { LigneFacture } from "./ligne-facture.model";

export class Facture {
  public clientId: number = 0;
  public lignes: LigneFacture[] = []
  public client: Client = new Client(0, '', '', '', 0, '', 'MASCULIN');
  constructor(
    public id: number,
    public note: string,
    public reference: string,
    public devise: string,
    public date: Date,
    public prixHT: number,
    public prixTVA: number,
    public prixTTC: number
  ) { }
}
