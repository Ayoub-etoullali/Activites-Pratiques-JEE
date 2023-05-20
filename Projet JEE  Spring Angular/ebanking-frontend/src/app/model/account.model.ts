export interface AccountDetails {
  id:            string;
  balance:              number;
  currentPage:          number;
  totalPage:           number;
  sizePage:             number;
  accountOperationDTOS: AccountOperation[];
}

export interface AccountOperation {
  id:            number;
  date: Date;
  amount:        number;
  type:          string;
  description:   string;
}
