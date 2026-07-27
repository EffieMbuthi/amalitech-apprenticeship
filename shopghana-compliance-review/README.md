# Multi-Jurisdiction Compliance & Risk Review: ShopGhana

**Module:** Data Governance and Integrity  
**Company:** ShopGhana  
**Scenario:** Global customer data deletion requests  
**Regulations:** Ghana Data Protection Act (Act 843), GDPR, CCPA/CPRA  

## Overview

ShopGhana operates across multiple jurisdictions and uses an internal **AI-based fraud and dispute-detection system** to flag accounts involved in payment disputes, chargebacks, or suspicious return activity.  

This system automatically pauses or delays account actions (including deletion requests) for flagged accounts pending review.

This review analyzes three customer deletion requests through three lenses:  
- **legal/compliance risk**  
- **data quality risk**  
- **AI bias risk**  

The fraud/dispute-detection system is the common thread across all three cases.

---

## 1. Customer A: Abena (Ghana)

### Legal Rights Analysis

Abena has the right to request deletion under Ghana's Data Protection Act (Act 843), but it is not absolute.

**Exemptions:** Legal obligations, public interest grounds.

**Company Obligations**
- Assess request and delete data where no legal obligation exists  
- Ensure compliance with retention requirements (e.g., financial records)  

**Data Retention**
- Can retain: Transaction/payment records (for legal/tax compliance)  
- Retention period: Based on Ghanaian financial regulations (typically several years)  

**Response Deadline:** Reasonable time (~21–30 days)

**Action Steps**
1. Verify identity  
2. Delete personal/profile data  
3. Retain only legally required records  
4. Log request for audit purposes  

**Draft Response**

> "Dear Abena, your request has been received and processed. Your personal account data has been deleted, except for records we are legally required to retain for compliance purposes."

---

### Data Quality Risks

- **Fragmented records:** Abena's profile may exist across separate regional systems (local Ghana database, backup archive, marketing tools) without a unified customer ID.  
- **Stale identity-verification data:** Outdated identity data could lead to rejecting valid requests or deleting the wrong record.  
- **Audit log integrity:** Logs must be accurate, timestamped, and protected from alteration.  

---

### AI Bias Risk: Fraud/Dispute-Detection System

Ghana's Act 843 does not yet contain GDPR-style automated-decision-making protections, so the main risk here is **under-scrutiny**, not overreach:

- Models trained on EU/US data may **misclassify Ghanaian behavior** (e.g., mobile money usage) as suspicious.  
- Lower regulatory pressure increases the risk that bias goes **unaudited**.  

**Mitigation:** Apply consistent fraud-review thresholds globally and require human review with justification.

---

## 2. Customer B: Lukas (Germany GDPR)

### Legal Rights Analysis

Lukas has a strong right to erasure (Article 17 - "Right to be Forgotten").

**Exemptions:** Legal compliance, defense of legal claims.

**Company Obligations**
- Delete personal data without undue delay  
- Inform third-party processors  
- Ensure GDPR compliance  

**Response Deadline:** 1 month (30 days)

**Penalties:** Up to €20 million or 4% of global turnover

**Action Steps**
1. Verify identity  
2. Delete data across all systems  
3. Notify third parties  
4. Confirm deletion  

**Draft Response**

> "Dear Lukas, we confirm that your personal data has been erased in accordance with GDPR Article 17. Any required legal records have been securely retained as permitted by law."

---

### Data Quality Risks

- **Third-party gaps:** Processors may not delete data due to incomplete tracking.  
- **Cross-border duplication:** Backups outside the EU may retain data.  
- **Timestamp inconsistency:** Poor tracking risks missing the 30-day deadline.  

---

### AI Bias Risk — Fraud/Dispute-Detection System

GDPR introduces strict rules via **Article 22**:

- Automated flags must include **human review**, not just system decisions.  
- Lack of explainability creates compliance risk.  

**Mitigation:** Ensure documented human review and provide clear explanations when requested.

---

## 3. Customer C: Maria (California CCPA/CPRA)

### Legal Rights Analysis

Maria has:
- Right to deletion  
- Right to opt-out of sale of personal data  

**Can Deletion Be Immediate?** No — due to an active dispute.

**Company Obligations**
- Pause deletion until dispute resolution  
- Immediately honor opt-out requests  

**Response Deadline:** 45 days (extendable to 90)

**Action Steps**
1. Verify identity  
2. Stop selling data immediately  
3. Notify user of delay  
4. Complete deletion after resolution  

**Draft Response**

> "Dear Maria, we have processed your request to stop the sale of your personal information. Your deletion request will be completed once your active return dispute is resolved."

---

### Data Quality Risks

- **Dispute sync issues:** Incorrect status may delay deletion unnecessarily.  
- **Opt-out lag:** Partners may still receive data briefly.  
- **Duplicate flags:** Multiple systems may over-retain data.  

---

### AI Bias Risk — Fraud/Dispute-Detection System

- ADMT rules require **transparency and possible opt-out**.  
- Bias in training data may over-flag certain groups.  
- Model may fail to distinguish real disputes vs fraud.  

**Mitigation:** Audit model behavior and ensure fair classification.

---

## 4. Compliance Comparison Table

| Element | Ghana DPA | GDPR | CCPA/CPRA |
|--------|----------|------|-----------|
| Right to Deletion | Yes (limited) | Strong | Yes |
| Deadline | ~21–30 days | 30 days | 45–90 days |
| Penalties | Regulatory | €20M / 4% revenue | $7,500 per violation |
| AI Safeguards | Limited | Strong (Art. 22) | Emerging (ADMT) |

---

## 5. Key Insights

- **GDPR** is the strictest regulation.  
- **CCPA/CPRA** emphasizes consumer control.  
- **Ghana DPA** allows flexibility but risks under-enforcement.  
- A **single flawed AI model** can create global compliance risks.  

---

## Conclusion

ShopGhana must align deletion processes with jurisdiction-specific laws while ensuring strong data governance and AI oversight. Poor data quality and biased automated systems can turn routine compliance tasks into legal risks across multiple regions.