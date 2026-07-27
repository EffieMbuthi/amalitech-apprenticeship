\# Multi-Jurisdiction Compliance \& Risk Review: ShopGhana



\*\*Module:\*\* Data Governance and Integrity

\*\*Company:\*\* ShopGhana

\*\*Scenario:\*\* Global customer data deletion requests

\*\*Regulations:\*\* Ghana Data Protection Act (Act 843), GDPR, CCPA/CPRA



\## Overview



ShopGhana operates across multiple jurisdictions and uses an internal \*\*AI-based fraud and dispute-detection system\*\* to flag accounts involved in payment disputes, chargebacks, or suspicious return activity. This system automatically pauses or delays account actions (including deletion requests) for flagged accounts pending review.



This review analyzes three customer deletion requests through three lenses: \*\*legal/compliance risk\*\*, \*\*data quality risk\*\*, and \*\*AI bias risk\*\*  using the fraud/dispute-detection system as the common thread across all three cases.



\---



\## 1. Customer A: Abena (Ghana)



\### Legal Rights Analysis

Abena has the right to request deletion under Ghana's Data Protection Act (Act 843), but it is not absolute.



\*\*Exemptions:\*\* Legal obligations, public interest grounds.



\*\*Company Obligations\*\*

\- Assess request and delete data where no legal obligation exists

\- Ensure compliance with retention requirements (e.g., financial records)



\*\*Data Retention\*\*

\- Can retain: Transaction/payment records (for legal/tax compliance)

\- Retention period: Based on Ghanaian financial regulations (typically several years)



\*\*Response Deadline:\*\* Reasonable time (commonly interpreted as \~21–30 days)



\*\*Action Steps\*\*

1\. Verify identity

2\. Delete personal/profile data

3\. Retain only legally required records

4\. Log request for audit purposes



\*\*Draft Response\*\*

> "Dear Abena, your request has been received and processed. Your personal account data has been deleted, except for records we are legally required to retain for compliance purposes."



\### Data Quality Risks

\- \*\*Fragmented records:\*\* Abena's profile may exist across separate regional systems (local Ghana database, backup archive, marketing tools) without a unified customer ID, making a "complete" deletion difficult to verify.

\- \*\*Stale identity-verification data:\*\* if the identity data used to confirm the request is outdated (old phone number, address), ShopGhana risks either wrongly rejecting a legitimate request or, worse, deleting the wrong record.

\- \*\*Audit log integrity:\*\* the "log request for audit purposes" step is only useful if the log itself is accurate, timestamped, and not later altered or lost, a common gap in smaller regional operations.



\### AI Bias Risk — Fraud/Dispute-Detection System

Ghana's Act 843 does not yet contain GDPR-style automated-decision-making protections, so the main risk here is \*\*under-scrutiny\*\*, not overreach:

\- If ShopGhana's fraud model was trained predominantly on transaction patterns from larger markets (EU/US), it may \*\*misclassify normal Ghanaian purchasing behavior\*\* (e.g., mobile money patterns, informal payment retries) as suspicious, unfairly flagging Abena's account and delaying her otherwise-valid deletion request.

\- Because Act 843 provides more organizational flexibility, there is a real risk this bias goes \*\*unaudited and undocumented\*\*, simply because the local regulatory pressure to justify automated flags is weaker than under GDPR or CCPA.



\*\*Mitigation:\*\* Apply the same fraud-review threshold and human-review safeguard used for EU/US customers, and log a justification whenever a Ghanaian account is flagged, to avoid regional under-protection.



\---



\## 2. Customer B: Lukas (Germany GDPR)



\### Legal Rights Analysis

Lukas has a strong right to erasure (Article 17 - "Right to be Forgotten").



\*\*Exemptions:\*\* Legal compliance (e.g., tax records), defense of legal claims.



\*\*Company Obligations\*\*

\- Delete personal data without undue delay

\- Inform third parties processing the data

\- Ensure full compliance with GDPR



\*\*Response Deadline:\*\* 1 month (30 days)



\*\*Penalties for Missing Deadline:\*\* Fines up to €20 million or 4% of global annual turnover



\*\*Action Steps\*\*

1\. Verify identity

2\. Delete personal data across all systems

3\. Notify third-party processors

4\. Confirm deletion to user



\*\*Draft Response\*\*

> "Dear Lukas, we confirm that your personal data has been erased in accordance with GDPR Article 17. Any required legal records have been securely retained as permitted by law."



\### Data Quality Risks

\- \*\*Third-party processor sync gaps:\*\* GDPR requires notifying processors of erasure, but if ShopGhana's data lineage tracking is incomplete, some processors (analytics vendors, ad partners) may not actually receive or act on the notice, leaving "ghost" copies of Lukas's data.

\- \*\*Cross-border data duplication:\*\* if Lukas's data was replicated to non-EU servers for backup or analytics, inconsistent regional data maps increase the risk that erasure is only partial.

\- \*\*Timestamp inconsistency:\*\* the 30-day deadline is only meaningful if the request intake time is recorded accurately and consistently across ShopGhana's systems, a common failure point when requests come through multiple channels (email, app, support ticket).



\### AI Bias Risk — Fraud/Dispute-Detection System

GDPR is the strictest lens here because \*\*Article 22\*\* gives data subjects the right not to be subject to a decision based solely on automated processing that produces legal or similarly significant effects, and delaying a deletion request based on an automated fraud flag could qualify.

\- If Lukas's account is flagged by the fraud model, ShopGhana must be able to show \*\*meaningful human review\*\*, not just an automated hold, or it risks a separate GDPR violation stacked on top of any erasure delay.

\- \*\*Explainability risk:\*\* GDPR also implies a right to meaningful information about the logic involved in automated decisions. If ShopGhana cannot explain \*why\* the model flagged Lukas's account, it cannot defend the delay if challenged.



\*\*Mitigation:\*\* Ensure every automated fraud flag affecting a deletion request is paired with a documented human review step and a plain-language explanation available on request.



\---



\## 3. Customer C: Maria (California CCPA/CPRA)



\### Legal Rights Analysis

Maria has:

\- Right to deletion

\- Right to opt-out of sale of personal data



\*\*Can Deletion Be Immediate?\*\* No - due to an active return dispute, data must be retained temporarily for dispute resolution.



\*\*Company Obligations\*\*

\- Pause deletion until dispute is resolved

\- Immediately honor "Do Not Sell My Personal Information" request



\*\*Response Deadline:\*\* 45 days (extendable to 90 with notice)



\*\*Required Disclosures\*\*

\- What data is collected

\- How it is used/shared

\- Confirmation of opt-out implementation



\*\*Action Steps\*\*

1\. Verify identity

2\. Stop selling personal data immediately

3\. Inform user deletion will occur after dispute resolution

4\. Complete deletion once obligation ends



\*\*Draft Response\*\*

> "Dear Maria, we have processed your request to stop the sale of your personal information. Your deletion request will be completed once your active return dispute is resolved, as permitted under applicable law."



\### Data Quality Risks

\- \*\*Dispute-status accuracy:\*\* the entire delay hinges on the dispute record being current and correct. If ShopGhana's dispute-tracking system is out of sync with the fraud model, Maria's data could be held indefinitely on a resolved dispute that was never marked closed.

\- \*\*Opt-out propagation lag:\*\* "immediately" honoring the do-not-sell request only works if all downstream ad/data-sharing partners are updated in real time, a data quality/integration gap could leave her data being sold briefly after the opt-out is logged.

\- \*\*Duplicate flags:\*\* if the fraud-detection system and the returns system independently flag her account without cross-referencing, ShopGhana could end up over-retaining data under two redundant "holds" past the point either is actually justified.



\### AI Bias Risk — Fraud/Dispute-Detection System

Maria's case is the direct origin of the fraud/dispute flag, so this is the clearest test case:

\- Under CPRA's newer \*\*Automated Decision-Making Technology (ADMT)\*\* rules, businesses must provide notice and, in many cases, an opt-out or human-review right when automated tools make decisions with legal or similarly significant effects, which plausibly includes delaying a deletion right.

\- \*\*Bias risk:\*\* if the fraud/dispute model was trained on historical return data that over-represents certain regions, purchase categories, or customer segments as "high dispute risk," it could disproportionately flag and delay deletion for those groups, a disparate-impact risk even without any intentional discrimination.

\- Since Maria's dispute is legitimate (not fraud), her case is a useful audit point: \*\*is the model actually distinguishing genuine disputes from fraudulent ones, or just flagging all disputes equally?\*\* If the latter, that's a bias/accuracy problem, not just a legal one.



\*\*Mitigation:\*\* Regularly audit the fraud model's flag rates across customer segments and dispute types; ensure Maria (and similar genuine-dispute customers) are not held to the same scrutiny as suspected-fraud accounts.



\---



\## 4. Compliance Comparison Table



| Element | Ghana DPA (Act 843) | GDPR | CCPA/CPRA |

|---|---|---|---|

| Right to Deletion Exists? | Yes (limited) | Yes (strong right) | Yes |

| Exemptions / Conditions | Legal obligations, public interest | Legal compliance, claims defense | Ongoing transactions, legal obligations |

| Response Deadline | Reasonable time (\~21–30 days) | 30 days | 45 days (extendable to 90) |

| Penalties for Non-Compliance | Regulatory sanctions | Up to €20M or 4% global revenue | Fines up to $7,500 per violation |

| Consent Requirements | Required for data processing | Explicit, informed consent | Opt-out rights for data sale |

| Automated Decision Safeguard | None explicit | Article 22 human review + explainability | ADMT rules — notice + opt-out/review |



\---



\## 5. Data Quality Risk Summary



| Risk | Description | Affected Customer(s) |

|---|---|---|

| Fragmented profiles | No unified ID across regional systems | Abena, Lukas |

| Stale verification data | Outdated identity data risks wrongful action | Abena |

| Incomplete data lineage | Third parties/backups not covered by deletion | Lukas |

| Timestamp inconsistency | Unclear intake time undermines deadline compliance | Lukas |

| Dispute-status sync gaps | Outdated dispute records extend holds indefinitely | Maria |

| Opt-out propagation lag | Downstream partners not updated in real time | Maria |

| Redundant flags | Overlapping holds with no cross-reference | Maria |



\## 6. AI Bias Risk Summary



| Risk | Description | Affected Customer(s) |

|---|---|---|

| Regional under-representation in training data | Normal local behavior misread as suspicious | Abena |

| Lack of human review on automated holds | Violates GDPR Article 22 expectations | Lukas |

| Lack of explainability | Cannot justify flag if challenged | Lukas |

| Disparate flag rates across segments | Certain groups over-flagged as high-risk | Maria |

| Fraud/genuine-dispute conflation | Model may not distinguish real disputes from fraud | Maria |



\---



\## 7. Key Insights

\- \*\*GDPR\*\* is the strictest jurisdiction, combining tight timelines, heavy penalties, and explicit automated-decision safeguards (Article 22).

\- \*\*CCPA/CPRA\*\* focuses strongly on consumer control, opt-out rights, and under CPRA's newer ADMT rules is catching up to GDPR on automated-decision accountability.

\- \*\*Ghana DPA\*\* provides real rights but more organizational flexibility, which creates a risk that AI bias issues go unaudited simply because local regulatory pressure is lighter.

\- Across all three cases, the \*\*same underlying fraud/dispute-detection system\*\* is the common risk source, meaning a single model design flaw or training bias could create compliance exposure in all three jurisdictions simultaneously, just under different legal names (erasure delay, ADMT violation, unaudited discrimination).



\## Conclusion



ShopGhana must handle each deletion request according to jurisdiction-specific law, but the risk analysis shows these obligations don't exist in isolation. Data quality gaps (fragmented records, sync failures, stale verification data) directly undermine the company's ability to meet legal deadlines, and the AI-driven fraud/dispute system that sits underneath all three cases introduces a bias risk that could turn a routine deletion delay into a separate legal violation, most acutely under GDPR and CPRA. Proper identity verification, unified data lineage, documented human review of automated flags, and regular bias audits of the fraud-detection model are essential to maintaining compliance and trust across all markets ShopGhana operates in.

