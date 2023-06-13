# language: da
Egenskab: Beregning af skat m. eksempler
  Som en skatteyder
  Vil jeg beregne min skat
  Så jeg ved, hvor meget jeg skylder

  Abstrakt Scenario: Beregning af skat for en skatteydere
    Givet en skatteyder med en indkomst på <indkomst>
    Når jeg beregner skatten
    Så skal skatten være <skat>

  Eksempler:
    | indkomst | skat     |
    |   1000    |   100   |
    |   9999    |   999,9 |
    |  10000    |  2000   |
    |  49999    |  9999,8 |
    |  50000    | 25000   |
    | 100000    | 50000   |