import { Facture } from "./facture.model";

export class Reglement {
  public factureId: number = 0;
  constructor(
    public id: number,
    public note: string,
    public reference: string,
    public date: Date,
    public montant: number,
    public type: string
  ) { }
}
